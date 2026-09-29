# UHospital

Java Swing desktop application for the IPC1 Project 1 hospital appointment assignment. This repository continues the original NetBeans project and its existing UI rather than replacing it with a separate application.

## Run

Requirements: Java 21+ and Maven.

```bash
mvn test
mvn exec:java
```

The administrator credentials specified by the assignment are code `202300476` and password `proyecto1IPC1`. Patients can register from the login screen; the administrator can register doctors and products. New account codes are displayed after registration.

## Features

- Administrator: create, update, and delete doctors, patients, and products; view the top five specialties and top three products.
- Doctor: publish available appointment times, view pending appointments, attend or reject them, and edit their profile.
- Patient: filter by specialty and doctor, request one pending appointment, view appointment history and pharmacy products, and edit their profile.
- Appointments keep their history after completion or rejection. Booking the same time twice is prevented.

Data lives in memory for the current run. The assignment does not require a database or disk persistence.

The original NetBeans metadata remains in `nbproject/`, but its old dependency paths reference Windows machines. Maven is the portable build path. The generated manuals are [technical](output/pdf/Manual_Tecnico.pdf) and [user](output/pdf/Manual_Usuario.pdf); their editable generator is [`docs/build_manuals.py`](docs/build_manuals.py).
