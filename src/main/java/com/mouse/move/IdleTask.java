package com.mouse.move;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinUser;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.robot.Robot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.mouse.move.Settings.IDLE_SLEEP_SECONDS;
import static com.mouse.move.Settings.IDLE_THRESHOLD_SECONDS;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public class IdleTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdleTask.class);

    private static final List<DayOfWeek> WEEKEND = Arrays.asList(SATURDAY, SUNDAY);

    private TextField textField;

    public IdleTask(TextField textField) {
        this.textField = textField;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                int idleTime = getIdleTime();
                if (idleTime >= IDLE_THRESHOLD_SECONDS && isRomanianWorkingHours()) {
                    LOGGER.debug("{} vs {}: will simulate presence", idleTime, IDLE_THRESHOLD_SECONDS);
                    simulatePresence();
                } else {
                    LOGGER.debug("{} vs {}: idle time is not enough/ is out of romanian working time/days to simulate presence", idleTime, IDLE_THRESHOLD_SECONDS);
                }
                TimeUnit.SECONDS.sleep(IDLE_SLEEP_SECONDS);

            }
        } catch (InterruptedException e) {
            LOGGER.debug("Idle task has finished due to interruption!");
        } catch (Exception e) {
            LOGGER.error("Idle task crashed due to {}: {}!", e);
        }
    }

    private int getIdleTime() {
        int tickCount = Kernel32.INSTANCE.GetTickCount();
        var lastInputInfo = new WinUser.LASTINPUTINFO();
        User32.INSTANCE.GetLastInputInfo(lastInputInfo);
        int idleTime = (tickCount - lastInputInfo.dwTime) / 1000;
        return idleTime;
    }

    private void simulatePresence() {
        Platform.runLater(() -> {
            var r = new Robot();
            if (textField.getText().isEmpty()) {
                textField.setText("a");
                r.mouseMove(r.getMouseX() + 5, r.getMouseY() + 5);
            } else {
                textField.clear();
                r.mouseMove(r.getMouseX() - 5, r.getMouseY() - 5);
            }
        });
    }

    private static boolean isRomanianWorkingHours() {
        LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("Europe/Bucharest"));

        int hour = dateTime.getHour();
        int min = dateTime.getMinute();
        int sec = dateTime.getSecond();

        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        LOGGER.debug("time is {} : {} : {} and day is {}", hour, min, sec, dayOfWeek);
        if (hour == 18 && (min != 0 || sec != 0)) {
            return false;
        }

        return (hour >= 10 && hour <= 18) && !WEEKEND.contains(dayOfWeek);
    }
}
