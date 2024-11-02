# Embedded Data Acquisition System - Accelerometer and Compass

This project represents an advanced embedded data acquisition system specifically developed to be integrated into a drone. The system relies on the use of an accelerometer and a compass to collect in-flight information, such as heading and experienced acceleration forces. Additionally, the system includes a real-time clock and an SD card for secure data storage.

## Contributors
- **Sandric Bretecher**: Responsible for programming the accelerometer and compass, as well as integrating the various modules.
- **Louis Musial**: Developed the communication protocol, including the creation of headers and labels to enable future communication via LoRa.

## Components Used
- **LSM303DLHC Sensor**: Module combining an accelerometer and a compass (magnetometer).
- **RTC2 Click Clock**: Real-time clock component ensuring data synchronization.
- **MicroSD Click Card**: Module for data storage on an SD card, allowing reliable data retention.
- **Arduino Uno Board**: Used as the development and control platform for the various components.
- **Arduino Click SHIELD Extension Board**: Provides an interface for connecting and integrating the different modules into the main system.

## Features
- **Real-Time Measurements**: Real-time acquisition of acceleration and heading data during flight.
- **Data Storage**: Acquired data is stored on an SD card for subsequent analysis.
- **Communication Protocol**: Implementation of a communication protocol for data transmission via LoRa to other systems (including altitude, pressure, speed, etc.).

## Communication Protocol
Louis Musial designed a robust communication protocol, including:
- **Frame Header**: Contains information such as sensor ID, timestamp, and the type of data being transmitted.
- **Labels and Data**: Definition of labels and data structure, including the maximum number of values per label, for optimal transmission via LoRa to other systems.

## Programming
Sandric Bretecher primarily contributed to the following aspects:
- **Accelerometer-Compass Programming**: Configuration, calibration of the sensors, in-flight data collection, and processing of raw information.
- **Module Integration**: Implementation and interconnection of the various hardware components to ensure system coherence and overall performance.

## Tests and Results
- **Calibration**: Careful calibration of the various modules (accelerometer, magnetometer, and clock) to ensure measurement accuracy.
- **Functional Tests**: In-depth system validation using data frames from the LSM303DLHC sensor, as well as tests on creating text files (.txt and .csv) for data verification.

## Challenges and Improvements
- **Challenges Encountered**: Difficulties with sensor registers and addresses, resolved through an in-depth study of datasheets and the use of an oscilloscope for signal verification.
- **Potential Improvements**: Full integration of the system into a drone, addition of a gyroscope to enrich measurements, and adoption of a real-time operating system (RTOS) for more precise task management.

## Installation
1. **Clone the repository**:
   ```
   git clone [repository URL]
   ```
2. **Deploy the code to Arduino**: Use the Arduino IDE to upload the code to the Arduino Uno board.
3. **Required Libraries**:
   - Adafruit_LSM303DLH_Mag.h
   - Adafruit_LSM303_Accel.h
   - Adafruit_Sensor.h
   - Wire.h
   - SD.h
   - RTClib.h

## License
This project is licensed under the MIT License - please see the [LICENSE](LICENSE) file for more details.

