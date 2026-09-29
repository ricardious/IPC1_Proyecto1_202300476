"""Generate the two UHospital manuals from the current application behavior."""

from pathlib import Path
from xml.sax.saxutils import escape

from reportlab.lib import colors
from reportlab.lib.enums import TA_CENTER
from reportlab.lib.pagesizes import A4
from reportlab.lib.styles import ParagraphStyle, getSampleStyleSheet
from reportlab.lib.units import cm
from reportlab.pdfbase import pdfmetrics
from reportlab.pdfbase.ttfonts import TTFont
from reportlab.platypus import (
    BaseDocTemplate, Frame, PageTemplate, Paragraph, Spacer, Table, TableStyle,
    PageBreak, KeepTogether,
)

ROOT = Path(__file__).resolve().parents[1]
OUT = ROOT / "output" / "pdf"
OUT.mkdir(parents=True, exist_ok=True)

FONT_DIR = Path("/usr/share/fonts/dejavu-sans-fonts")
pdfmetrics.registerFont(TTFont("DejaVu", str(FONT_DIR / "DejaVuSans.ttf")))
pdfmetrics.registerFont(TTFont("DejaVu-Bold", str(FONT_DIR / "DejaVuSans-Bold.ttf")))
pdfmetrics.registerFontFamily("DejaVu", normal="DejaVu", bold="DejaVu-Bold")

NAVY = colors.HexColor("#183047")
BLUE = colors.HexColor("#1B6D93")
LIGHT = colors.HexColor("#EAF3F7")
INK = colors.HexColor("#263442")

styles = getSampleStyleSheet()
styles.add(ParagraphStyle(name="CoverTitleUH", fontName="DejaVu-Bold", fontSize=23, leading=29,
                          textColor=NAVY, alignment=TA_CENTER, spaceAfter=18))
styles.add(ParagraphStyle(name="CoverSubUH", fontName="DejaVu", fontSize=12, leading=18,
                          textColor=BLUE, alignment=TA_CENTER, spaceAfter=18))
styles.add(ParagraphStyle(name="H1UH", fontName="DejaVu-Bold", fontSize=15, leading=20,
                          textColor=BLUE, spaceBefore=17, spaceAfter=8, keepWithNext=1))
styles.add(ParagraphStyle(name="H2UH", fontName="DejaVu-Bold", fontSize=10.5, leading=15,
                          textColor=NAVY, spaceBefore=11, spaceAfter=5, keepWithNext=1))
styles.add(ParagraphStyle(name="BodyUH", fontName="DejaVu", fontSize=8.8, leading=14,
                          textColor=INK, spaceAfter=7))
styles.add(ParagraphStyle(name="SmallUH", fontName="DejaVu", fontSize=7.5, leading=11,
                          textColor=INK, spaceAfter=4))
styles.add(ParagraphStyle(name="CellUH", fontName="DejaVu", fontSize=7.7, leading=11, textColor=INK))
styles.add(ParagraphStyle(name="CellHeadUH", fontName="DejaVu-Bold", fontSize=7.7, leading=11,
                          textColor=colors.white))


def para(text, style="BodyUH"):
    return Paragraph(escape(text), styles[style])


def h1(text):
    return para(text, "H1UH")


def h2(text):
    return para(text, "H2UH")


def bullets(items):
    return [para("• " + item) for item in items]


def table(headers, rows, widths):
    data = [[para(x, "CellHeadUH") for x in headers]]
    data += [[para(str(x), "CellUH") for x in row] for row in rows]
    result = Table(data, colWidths=widths, repeatRows=1, hAlign="LEFT")
    result.setStyle(TableStyle([
        ("BACKGROUND", (0, 0), (-1, 0), BLUE),
        ("ROWBACKGROUNDS", (0, 1), (-1, -1), [colors.white, LIGHT]),
        ("VALIGN", (0, 0), (-1, -1), "TOP"),
        ("BOX", (0, 0), (-1, -1), 0.5, colors.HexColor("#B8CDD8")),
        ("INNERGRID", (0, 0), (-1, -1), 0.25, colors.HexColor("#D7E5EB")),
        ("LEFTPADDING", (0, 0), (-1, -1), 7),
        ("RIGHTPADDING", (0, 0), (-1, -1), 7),
        ("TOPPADDING", (0, 0), (-1, -1), 7),
        ("BOTTOMPADDING", (0, 0), (-1, -1), 7),
    ]))
    return result


def flow(steps):
    rows = []
    for index, step in enumerate(steps):
        rows.append([para(step, "CellUH")])
        if index < len(steps) - 1:
            rows.append([para("↓", "CellUH")])
    diagram = Table(rows, colWidths=[16.6 * cm], hAlign="CENTER")
    diagram.setStyle(TableStyle([
        ("BACKGROUND", (0, 0), (-1, -1), LIGHT),
        ("BOX", (0, 0), (-1, -1), 0.5, colors.HexColor("#B8CDD8")),
        ("ALIGN", (0, 0), (-1, -1), "CENTER"),
        ("VALIGN", (0, 0), (-1, -1), "MIDDLE"),
        ("TOPPADDING", (0, 0), (-1, -1), 5),
        ("BOTTOMPADDING", (0, 0), (-1, -1), 5),
    ]))
    return diagram


def frame(canvas, doc):
    canvas.saveState()
    w, h = A4
    canvas.setFillColor(NAVY)
    canvas.rect(0, h - 1.2 * cm, w, 1.2 * cm, fill=1, stroke=0)
    canvas.setFont("DejaVu-Bold", 9)
    canvas.setFillColor(colors.white)
    canvas.drawString(1.8 * cm, h - 0.8 * cm, "UHOSPITAL  |  IPC1 PROYECTO 1")
    canvas.setFillColor(INK)
    canvas.setFont("DejaVu", 7)
    canvas.drawString(1.8 * cm, 1.05 * cm, "Universidad de San Carlos de Guatemala  •  202300476")
    canvas.drawRightString(w - 1.8 * cm, 1.05 * cm, str(doc.page))
    canvas.restoreState()


def build(filename, story):
    doc = BaseDocTemplate(str(OUT / filename), pagesize=A4,
                          leftMargin=1.8 * cm, rightMargin=1.8 * cm,
                          topMargin=1.8 * cm, bottomMargin=1.55 * cm)
    usable = Frame(doc.leftMargin, doc.bottomMargin, doc.width, doc.height,
                   leftPadding=0, rightPadding=0, topPadding=0, bottomPadding=0)
    doc.addPageTemplates(PageTemplate(id="manual", frames=[usable], onPage=frame))
    expanded = []
    for item in story:
        if isinstance(item, list):
            expanded.extend(item)
        else:
            expanded.append(item)
    doc.build(expanded)


def cover(title, subtitle):
    return [Spacer(1, 2.2 * cm), para(title, "CoverTitleUH"), para(subtitle, "CoverSubUH"),
            para("Sistema de control de citas de hospital UHospital", "CoverSubUH"),
            Spacer(1, 1.2 * cm),
            para("Proyecto 1 de Introducción a la Programación y Computación 1."),
            para("Esta edición documenta la aplicación implementada en el repositorio IPC1_Proyecto1_202300476."),
            PageBreak()]


technical = cover("Manual técnico", "Arquitectura, funciones y procesos")
technical += [
    h1("1. Alcance y ejecución"),
    para("Aplicación Java 21 de escritorio con AWT/Swing, FlatLaf para el aspecto visual y JFreeChart para los reportes. "
         "La información reside en memoria durante la ejecución; el enunciado no exige almacenamiento persistente."),
    table(["Comando", "Uso"], [
        ("mvn test", "Compila el código y ejecuta las pruebas del flujo de citas."),
        ("mvn exec:java", "Inicia la interfaz gráfica desde la raíz del repositorio."),
        ("mvn package", "Genera el artefacto de compilación dentro de target/."),
    ], [5.0 * cm, 11.6 * cm]),
    h2("Estructura del código"),
    table(["Paquete", "Responsabilidad"], [
        ("controlador.Main", "Punto de entrada, colecciones en memoria, códigos y reglas compartidas de las citas."),
        ("modelo", "DOCTOR, PACIENTE, PRODUCTO, HORARIO y CITA; CITA contiene el estado."),
        ("views", "LOGIN, REGISTER, ADMINISTRADOR, vtnPACIENTE, vtnDOCTOR y formularios de mantenimiento."),
        ("test", "Pruebas JUnit del flujo de citas y sus restricciones."),
    ], [4.1 * cm, 12.5 * cm]),
    h1("2. Modelo y relaciones"),
    para("Main mantiene listas de doctores, pacientes, productos, horarios y citas. Cada HORARIO referencia al código "
         "de su doctor. Cada CITA referencia los códigos de paciente y doctor, la fecha y hora, el motivo y su estado. "
         "Los códigos de persona/producto y el número de cita se incrementan al crear registros."),
    flow(["DOCTOR (código, especialidad) → publica HORARIO (doctor, fecha/hora)",
          "PACIENTE (código) → solicita CITA (paciente, doctor, horario, motivo)",
          "CITA: PENDIENTE → COMPLETADA o RECHAZADA; el historial se conserva"]),
    h2("Estados y restricciones"),
    bullets([
        "Solo se publica una fecha futura; un doctor no puede publicar dos veces la misma hora.",
        "Solo se reserva un horario publicado y libre. No se admite más de una cita pendiente por paciente.",
        "Una cita completada conserva el horario ocupado; una rechazada libera el horario.",
        "Solo el doctor asignado puede completar o rechazar una cita pendiente.",
    ]),
    PageBreak(),
    h1("3. Funciones principales del controlador"),
    table(["Método", "Función / resultado"], [
        ("agregarPaciente(...) / agregarDoctor(...) / agregarProducto(...)", "Crea y añade cada entidad a su lista; la interfaz asigna el código incremental."),
        ("obtenerDoctorPorCodigo(int) / obtenerPacientePorCodigo(int) / obtenerProductoPorCodigo(int)", "Busca un registro por su identificador; devuelve null si no existe."),
        ("convertirDatosDoctor_Tabla() / convertirDatosPaciente_Tabla() / convertirDatosProductos_Tabla()", "Prepara filas para las tablas del administrador."),
        ("publicarHorario(DOCTOR, LocalDateTime)", "Valida doctor, fecha futura y duplicados; agrega un HORARIO."),
        ("horarioDisponible(int, LocalDateTime)", "Comprueba publicación, futuro y que no esté ocupado por una cita no rechazada."),
        ("tieneCitaPendiente(int)", "Busca una cita pendiente del paciente; impide una segunda solicitud."),
        ("solicitarCita(PACIENTE, DOCTOR, LocalDateTime, String)", "Valida selección, motivo y disponibilidad; registra una CITA pendiente con número único."),
        ("cambiarEstadoCita(CITA, DOCTOR, Estado)", "Autoriza al doctor asignado y cambia una cita pendiente a completada o rechazada."),
    ], [7.6 * cm, 9.0 * cm]),
    h1("4. Flujo: registro e inicio de sesión"),
    flow(["Paciente introduce nombres, apellidos, edad, sexo y contraseña; se valida la entrada",
          "Main asigna un código único; la interfaz lo muestra al usuario",
          "LOGIN compara código y contraseña: administrador, paciente o doctor",
          "Se abre el módulo correspondiente; credenciales inválidas muestran un error"]),
    h1("5. Flujo: administración"),
    flow(["Seleccionar Doctores, Pacientes o Productos y pulsar Crear / Actualizar / Eliminar",
          "Validar campos y, para cambios o borrado, buscar el código y confirmar",
          "Modificar la colección; rechazar citas pendientes al eliminar un doctor o paciente",
          "Reconstruir tablas y gráficas: top 5 especialidades / top 3 productos"]),
    h1("6. Flujo: citas y horarios"),
    flow(["Doctor inicia sesión y publica fecha/hora futura en Asignar horario",
          "Paciente filtra especialidad → doctor → horario libre; escribe el motivo",
          "Crear cita registra PENDIENTE; el paciente ve número, fecha, hora y estado",
          "Doctor ve detalles y pulsa Atender o Rechazar; el estado queda en el historial"]),
    h2("Interfaz de usuario"),
    bullets([
        "ADMINISTRADOR mantiene las tres pestañas originales y muestra tablas y gráficas calculadas sobre los datos actuales.",
        "vtnPACIENTE contiene Solicitar Cita, Ver Estado de Cita y Farmacia, más Editar perfil y Cerrar sesión.",
        "vtnDOCTOR contiene Citas y Asignar horario, más Editar perfil y Cerrar sesión.",
        "Los formularios usan mensajes de error para entradas inválidas y confirmación para operaciones destructivas.",
    ]),
    h1("7. Validación y pruebas"),
    para("FlujoCitasTest crea doctor y paciente, publica una hora futura, reserva, comprueba que no se pueda duplicar, "
         "completa la cita y verifica que no se pueda modificar de nuevo. Ejecute `mvn test` antes de entregar."),
    h2("Límites conocidos del alcance"),
    para("El PDF de la práctica no pide base de datos ni persistencia: al cerrar la aplicación los datos en memoria se pierden. "
         "Tampoco define un módulo de enfermería, aunque una descripción menciona ese rol al hablar de los estados. "
         "La transición implementada está a cargo del doctor, como especifica su módulo."),
]


user = cover("Manual de usuario", "Cómo utilizar UHospital")
user += [
    h1("1. Iniciar la aplicación"),
    para("Instale Java 21 y Maven. En la carpeta del proyecto ejecute `mvn exec:java`. Aparecerá la ventana LOGIN. "
         "Si se requiere comprobar primero el proyecto, ejecute `mvn test`."),
    h2("Ingresar o registrarse"),
    bullets([
        "Administrador: código 202300476 y contraseña proyecto1IPC1 (definidos por el enunciado).",
        "Paciente nuevo: pulse Registrarse, complete nombres, apellidos, contraseña, sexo y edad positiva. Anote el código mostrado.",
        "Paciente o doctor existente: escriba su código y contraseña y pulse Iniciar Sesión.",
        "El icono de ojo muestra u oculta la contraseña; un error de credenciales se informa sin abrir ningún módulo.",
    ]),
    h1("2. Administrador"),
    para("Las pestañas Doctores, Pacientes y Productos contienen listas de registros y los botones de mantenimiento. "
         "El código de cada registro aparece en su tabla; consérvelo para actualizar o eliminar."),
    table(["Acción", "Qué hacer"], [
        ("Crear", "Abra el formulario, complete los campos obligatorios y confirme. Se mostrará el nuevo código."),
        ("Actualizar", "Indique el código existente; modifique datos y guarde. Un código inexistente muestra error."),
        ("Eliminar", "Indique el código existente y confirme. La lista se actualiza al terminar."),
        ("Reportes", "Doctores: top 5 especialidades. Productos: top 3 cantidades disponibles."),
    ], [3.5 * cm, 13.1 * cm]),
    para("Al borrar un doctor o paciente, sus citas pendientes pasan a Rechazada. Cerrar sesión vuelve al login."),
    PageBreak(),
    h1("3. Paciente"),
    h2("Solicitar cita"),
    bullets([
        "Abra Solicitar Cita y elija primero una especialidad disponible.",
        "Elija un doctor de esa especialidad y una fecha/hora que él haya publicado.",
        "Escriba el motivo y pulse Crear cita. Aparecerá el número y la fecha de la reserva.",
        "Mientras tenga una cita pendiente, no podrá solicitar otra. Un horario reservado no se ofrece a otros pacientes.",
    ]),
    h2("Estado, farmacia y perfil"),
    bullets([
        "Ver Estado de Cita muestra número, fecha/hora, doctor y estado: PENDIENTE, COMPLETADA o RECHAZADA.",
        "Farmacia muestra los productos que tienen cantidad mayor que cero, con descripción y precio.",
        "Editar perfil permite cambiar nombres, apellidos, edad y contraseña; el código no cambia.",
    ]),
    h1("4. Doctor"),
    h2("Publicar horario"),
    para("En Asignar horario escriba una fecha futura con formato dd/MM/yyyy HH:mm, por ejemplo 25/12/2026 09:30, "
         "y pulse Asignar. No se admiten fechas pasadas ni la misma hora duplicada para ese doctor."),
    h2("Atender citas"),
    para("En Citas seleccione una solicitud. Ver más muestra paciente, fecha y motivo. Atender la marca "
         "COMPLETADA; Rechazar la marca RECHAZADA. En ambos casos desaparece de la cola del doctor, "
         "pero el paciente conserva el registro en su historial."),
    para("Editar perfil modifica nombres, apellidos, contraseña, edad y especialidad. Cerrar sesión vuelve al login."),
    PageBreak(),
    h1("5. Problemas frecuentes"),
    table(["Situación", "Revisión"], [
        ("No aparecen doctores u horarios", "El administrador debe crear un doctor y el doctor debe publicar una hora futura."),
        ("No se habilita Crear cita", "Ya existe una cita pendiente del paciente; espere a que el doctor la atienda o rechace."),
        ("Código o contraseña inválidos", "Compruebe el código comunicado al registrar el usuario y la contraseña exacta."),
        ("No se ven datos después de reiniciar", "Los registros solo se guardan en memoria mientras la aplicación está abierta."),
        ("Error de compilación en NetBeans", "Las rutas históricas de bibliotecas apuntan a Windows; use Maven en esta máquina."),
    ], [5.0 * cm, 11.6 * cm]),
    h1("6. Buenas prácticas"),
    bullets([
        "Anote el código de doctor o paciente que aparece al registrarlo; es necesario para iniciar sesión.",
        "Confirme el código antes de eliminar un registro, porque el borrado no se puede deshacer durante la sesión.",
        "Cierre sesión al terminar, especialmente si comparte la computadora.",
    ]),
]


if __name__ == "__main__":
    build("Manual_Tecnico.pdf", technical)
    build("Manual_Usuario.pdf", user)
    print("Manuals generated in", OUT)
