# mouseMove

## 1. What is it?
Very basic application written in java 17 & javafx 21 that will simulate presence ( move the mouse cursor and type a random character )
if it detects that:
- you are between 10 and 18 working romanian hours
- you were idle for at least 15 seconds

## 2. How does it work?
The app makes use of the `javafx.scene.robot.Robot` class in order to move the mouse cursor and `jna`'s `WIN32 API` to detect the idle time.

You **DO NOT** need to close the application ever. It will automatically detect working days/ working hours and idle time in order to figure out if it needs to simulate presence or not. 

## 3. Configuration
The configuration is hard coded in the code ( I was lazy but feel free to change it). You can change the following things:
- `IDLE_THRESHOLD_SECONDS` in `com.mouse.move.Settings` ( after how many seconds to simulate presence ).
- `IDLE_SLEEP_SECONDS` in `com.mouse.move.Settings` ( how much time to sleep is threshold time wasn't reached ).
- `WORK_START_TIME` in `com.mouse.move.IdleTask` ( at what time the application should activate ).
- `WORK_END_TIME` in `com.mouse.move.IdleTask` ( at what time the application should deactivate ).
- `WEEKEND` in `com.mouse.move.IdleTask` ( set the weekend days ).

**NOTE:**  `WORK_START_TIME/ WORK_END_TIME` are used in order to leave your laptop to go to sleep.

## 4. Supported operating systems
Unfortunately, the application only supports Windows ( was tested on 10 & 11 ), but can be easily extended to work on other systems ( needs to adapt the idle time native call considering the running OS  ).

# 5.Finally
Feel free to make a MR or reach dragomir.aurelian@gmail.com. Cheers!

