# Code-Notes

โครงงานพัฒนาซอฟต์แวร์ Text Editor สำหรับนักพัฒนาหน้าใหม่ โครงานนี้เป็นส่วนหนึ่งของรายวิชาการสร้างโปรแกรมเชิงอ็อบเจกต์ - Object-Oriented Programming (06016317)

![](fxversion.png)

## Features
> คุณสมบัติของโปรแกรม

**Code-Notes** เป็นโปรแกรมแก้ไขข้อความ (Text Editor) โดยจะมีคุณสมบัติของโปรแกรมดังนี้คือ

- **Text Editor | ส่วนการแก้ไขข้อความ**
  - Tabbed Editor | รองรับการเปิดไฟล์หลายแทบ
  - Directory Listing | เปิดดูไฟล์จากภายในเครื่องจากภายในโปรแกรมได้
  - Syntax Highlighting | เน้นสีข้อความในชุดคำสั่ง
  - Code Folding | การย่อเพื่อปิดบางส่วนในชุดคำสั่งชั่วคราว
- **Exception Lookup | ระบบช่วยค้นหาข้อผิดพลาด**
  - ค้นหาจากข้อผิดพลาดที่ได้รับตอนรัน/คอมไพล์โปรแกรม
  - ช่วยบอกวิธีการแก้ไข/จุดสังเกตที่มักทำให้เกิดข้อผิดพลาด
  - ช่วยค้นหาข้อมูลเพิ่มเติมหากยังแก้ไข่ไม่ได้ หรือต้องการเรียนรู้เพิ่มเติม
- **อื่น ๆ**
  - Internationalization (i18n) | รองรับสองภาษา (อังกฤษ/ไทย)
  - Preferences | ตั้งค่าส่วนต่าง ๆ ภายในโปรแกรม
    
## Built-With
> เครื่องมือที่ใช้

- Java
  - JavaFX
  - [RSyntaxTextArea](https://github.com/bobbylight/RSyntaxTextArea)
- IDE/Tools
  - Netbeans 8.2
  - JavaFX Scene Builder 2.0
  
## Build

1. Clone Repository นี้
2. เข้าไปยัง Root Directory ของ Repository
```
cd Code-Notes
```
3. รัน Ant Script
```
ant
```
