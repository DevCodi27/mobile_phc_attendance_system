package com.phc_system.attendance;

import com.mantra.mfs100.MFS100;
import com.mantra.mfs100.MFS100Event;
import com.mantra.mfs100.FingerData;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Scanner;

public class CaptureFingerprint implements MFS100Event {

    private static MFS100 mfs100;
    private static CaptureFingerprint instance;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            instance = new CaptureFingerprint();
            mfs100 = new MFS100(instance);
            mfs100.Init();

            if (!mfs100.IsConnected()) {
                System.out.println("MFS100 not connected.");
                return;
            }

            System.out.println("MFS100 connected.");
            System.out.println("Type 'capture' to scan fingerprint or 'exit' to quit.");

            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim().toLowerCase();

                if (input.equals("exit")) {
                    System.out.println("Exiting...");
                    break;
                } else if (input.equals("capture")) {
                    System.out.println("Place your finger on the device...");

                    FingerData fingerData = new FingerData();
                    int ret = mfs100.AutoCapture(fingerData, 10000, true); // 10 seconds timeout

                    if (ret == 0) {
                        System.out.println("Fingerprint captured successfully.");
                        byte[] template = fingerData.ISOTemplate();
                        String base64 = Base64.getEncoder().encodeToString(template);
                        sendToAPI(base64);
                    } else {
                        System.out.println("Capture failed. Error code: " + ret);
                    }
                } else {
                    System.out.println("Unknown command. Please type 'capture' or 'exit'.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mfs100 != null) {
                mfs100.UnInit();
            }
            scanner.close();
        }
    }


    private static void sendToAPI(String base64Fingerprint) {
        try {
            String apiUrl = "https://your-api.com/fingerprint/upload"; // Update this
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            String jsonPayload = "{ \"fingerprint_data\": \"" + base64Fingerprint + "\" }";
            try (OutputStream os = con.getOutputStream()) {
                os.write(jsonPayload.getBytes());
                os.flush();
            }

            int responseCode = con.getResponseCode();
            System.out.println("API Response Code: " + responseCode);

        } catch (Exception e) {
            System.out.println("Failed to send fingerprint to API:");
            e.printStackTrace();
        }
    }


    @Override
    public void OnDeviceAttached(int vid, int pid, boolean hasPermission) {
        System.out.println("Device attached:");
        System.out.println("VID: " + vid + ", PID: " + pid);
        if (hasPermission) {
            System.out.println("Permission granted to access fingerprint device.");
        } else {
            System.out.println("Permission denied. Please check USB permissions.");
        }
    }

    @Override
    public void OnDeviceDetached() {
        System.out.println("Fingerprint device disconnected.");
    }

    @Override
    public void OnHostCheckFailed(String errMsg) {
        System.out.println("Host check failed:");
        System.out.println("Error message: " + errMsg);
        System.out.println("Please ensure the driver is installed and the device is genuine.");
    }
}
