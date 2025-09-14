# Integrated-System-for-Experiment-Analysis-in-a-Physics-Laboratory
The source code for my Bachellor's degree Thesis!  

The project consists of a Desktop aplication and an Arduino UNO program interfacing with each other and outputting sensor data to save them on an SQL database or extact them into .csv files.

This thesis was made in 2018-2019, when Venezuela was in a severe economic crisis. Our original scope was to recreate a PASCO laboratory equipment using an Arduino Device for our university, because the original hardware was unsupported on modern version of Microsoft® Windows.

However, due  to difficulties in finding the necessary hardware and monetary constrains we had to scale it down into just emulating two out of the 8+ available sensors.

The system can read data from a free fall sensor and a temperature sensor. 


The following are needed to run the program.

Software:
- Netbeans ver. 8.2
- Arduino IDE
- Java 8
- MySQL

The following hardware is needed.
- Arduino UNO
- USB cable
-  '1/4 Female Audio Jack
- LM35 heat sensor
-  PASCO ME-9207B

## Used Libraries
[Libreria PanamaHitek_Arduino Files](https://sourceforge.net/projects/arduinoyjava/files/v3.0.0/)

[RXTX serial package for Java](https://web.archive.org/web/20180309091349/http://fizzed.com/oss/rxtx-for-java)

[jfreechart-1.0.18](https://web.archive.org/web/20250911020528/https://www.jfree.org/jfreechart/)


## Links to the Thesis research paper.

[My Complete Thesis on Google Drive](https://drive.google.com/drive/folders/1_B_JC4UiiiuSr_Z-TOKZALPW6rjOf3np?usp=sharing)



[Opening Statement and Abstract](https://virtual.urbe.edu/tesispub/0108582/Preliminares.pdf)

[Chapter 1](https://virtual.urbe.edu/tesispub/0108582/cap01.pdf)

[Chapter 2](https://virtual.urbe.edu/tesispub/0108582/cap02.pdf)

[Chapter 3](https://virtual.urbe.edu/tesispub/0108582/cap03.pdf)

[Chapter 4](https://virtual.urbe.edu/tesispub/0108582/cap04.pdf)

[Conclusions](https://virtual.urbe.edu/tesispub/0108582/Finales.pdf)



## Project Photos 

### System Diagram
<img width="551" height="311" alt="image" src="https://github.com/user-attachments/assets/5b51547b-b59b-4f66-b8a1-a7803712f8d6" />

### System Schematics
<img width="552" height="385" alt="image" src="https://github.com/user-attachments/assets/d04ad229-c729-44be-9c4f-354381db1372" />

### Database schema
<img width="458" height="409" alt="image" src="https://github.com/user-attachments/assets/635ba306-c190-467d-8a63-d3f31de555ab" />


### Proto-board photo
<img width="768" height="425" alt="image" src="https://github.com/user-attachments/assets/dbcb93bb-7767-4d0c-aea7-5828d6f32baa" />

### Temperature Test
<img width="400" height="321" alt="image" src="https://github.com/user-attachments/assets/a224579c-cae9-4808-bcc6-32bae0bb0bc5" />
</br>
<img width="300" height="400" alt="image" src="https://github.com/user-attachments/assets/ce6ab6cc-9695-4013-ba6f-73367d2282f5" />
<img width="300" height="230" alt="image" src="https://github.com/user-attachments/assets/7a559830-0c95-42b8-95fe-af53797466a3" />


### Free Fall Test
<img width="376" height="259" alt="image" src="https://github.com/user-attachments/assets/4da77790-17c8-414d-b55b-1a4d16f9ddd7" />

### Test Database
<img width="552" height="249" alt="image" src="https://github.com/user-attachments/assets/756713be-4273-44d6-8a56-977a1b004275" />



## Thesis Abstract
<b>Rivero, Lorenzo; Toro, Eduardo and Medina, Gustavo. “Integrated System for Experiment Analysis in a Physics Laboratory”. Private University Dr. Rafael Belloso Chacín. Faculty of Engineering. School of Computing. Maracaibo. 2019.</b>
 


<b><p align="center">ABSTRACT</p></b>


In this present degree thesis an Integrated System for Experiment Analysis in a Physics laboratory was developed, the system has the purpose of being able to read data from the sensors made by the company PASCO Scientific, which were part of an experiment system developed by the same company, but were left unused because of the system’s obsolescence. The theoretical bases of the investigation were supported by the manuals made by PASCO Scientific (1995), and in the theoretical approaches made by the authors Pan, Zhu (2017) and Wilshurt (2010). The research was descriptive, projective, documental and non-experimental. As data collection techniques direct observation, structured interview and document revision were used. The methodology for this research was a hybrid between the strategies purposed by James Senn (1992), and the V-Model Software Development methodology by Ronald Tyson (2018). The following phases where constructed as a result: (PHASE I): Preliminary Investigation, (PHASE II) Definition of Specifications, (PHASE III): Global Design, (PHASE IV) Design in detail, (PHASE V) Implementation, (PHASE VI), Unit Testing, (PHASE VII) Integration, and (PHASE VIII) System operational Test. The result of the investigation, was the completion of the general objective, which was the development of the Integrated System for Experiment Analysis in a Physics Laboratory. In conclusion, the system is capable of obtaining de data signals from the sensors by the use of an Arduino board, to then process the signals and make a graphic that can be seen on a personal computer, with the option of being able to save the data. One of the recommendations is to include more entry ports for sensors, since the system only counts with one entry port, and would be convenient in the case that more experiment sensors become supported. 



Keywords: Integrated system, Laboratory, experiment, experiment analysis


(C) 2019,2025 Eduardo Toro
