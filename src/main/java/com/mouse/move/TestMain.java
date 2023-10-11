package com.mouse.move;

import com.sun.jna.Memory;
import com.sun.jna.platform.win32.PowrProf;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinNT;

import java.util.concurrent.TimeUnit;

public class TestMain {
    public static void main(String[] args) throws InterruptedException {
//        WinNT.SYSTEM_POWER_POLICY systemPowerPolicy= new WinNT.SYSTEM_POWER_POLICY();
//        Pointer pointer=systemPowerPolicy.getPointer();
//        PowrProf.INSTANCE.CallNtPowerInformation(PowrProf.POWER_INFORMATION_LEVEL.SystemPowerPolicyCurrent,
//                Pointer.NULL,0, pointer, systemPowerPolicy.size());
//        System.out.println(systemPowerPolicy.VideoDimDisplay);
//        System.out.println(systemPowerPolicy.VideoTimeout);

        int size = new WinNT.SYSTEM_POWER_INFORMATION().size();
        Memory mem = new Memory(size);
        var res = PowrProf.INSTANCE
                .CallNtPowerInformation(PowrProf.POWER_INFORMATION_LEVEL.SystemPowerInformation, null, 0, mem, size);
        WinNT.SYSTEM_POWER_INFORMATION power = new WinNT.SYSTEM_POWER_INFORMATION(mem);
        System.out.println("result is " + res);

        System.out.println(power.TimeRemaining);
        TimeUnit.SECONDS.sleep(5);


    }
}
