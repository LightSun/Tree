package com.heaven7.java.tree;

public class PathHandler {

    static String CONS =
            "%INTEL_DEV_REDIST%redist\\intel64\\compiler;" +
                    "C:\\ProgramData\\Oracle\\Java\\javapath;" +
                    "C:\\windows\\system32;C:\\windows;" +
                    "C:\\windows\\System32\\Wbem;C:\\windows\\System32\\WindowsPowerShell\\v1.0\\;C:\\Program Files (x86)\\NVIDIA Corporation" +
            "\\PhysX\\Common;D:\\study\\gradle-4.4.1\\bin;D:\\study\\maven\\apache-maven-3.5.2\\bin;D:\\Program Files\\Git\\cmd;E:\\Program " +
            "Files\\TortoiseSVN\\bin;%ANDROID_HOME%\\platform-tools;%JDK_HOME%\\bin;D:\\Program Files\\py_depend;%AutoIt3%;D:\\Program " +
            "Files (x86)\\QuickTime\\QTSystem\\;E:\\android\\sdk\\ndk-bundle;F:\\ffmpeg-win64-static\\bin;%Python_36%\\Scripts;%Flutter" +
            "%;E:\\Program Files\\TortoiseGit\\bin;%Protoc%;%Maven_Home%\\bin;D:\\Program Files\\CMake\\bin;C:\\Program Files\\dotnet" +
            "\\;C:\\Program Files\\MySQL\\MySQL Utilities 1.6\\;C:\\Program Files (x86)\\Windows Kits\\8.1\\bin\\x64;%VS_runtime%\\x64;" +
            "%Repo%;C:\\ProgramData\\chocolatey\\bin;;E:\\study\\dart\\Dart\\dart-sdk\\bin;E:\\study\\lua\\lua-0.9.8-Windows-x86\\bin;D:" +
            "\\Program Files\\nodejs\\;%Python_36%";
    public static void main(String[] args) {
        String[] strs = CONS.split(";");
        for (String str : strs){
            System.out.println(str);
        }
        System.out.println(CONS);
    }
}
