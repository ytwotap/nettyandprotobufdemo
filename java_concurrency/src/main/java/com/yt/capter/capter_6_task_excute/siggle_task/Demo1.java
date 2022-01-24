package com.yt.capter.capter_6_task_excute.siggle_task;

import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 在线程中执行任务
 * @author: YT
 * @date: 2022/1/21/021
 */
public class Demo1 {
    @Test
    public void startSocket() throws IOException {
        ServerSocket socket = new ServerSocket(8088);
        while (true) {
            Socket accept = socket.accept();
            handelerRequest(accept);
        }
    }

    private void handelerRequest(Socket accept) throws IOException {
        InputStream input = accept.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String line = reader.readLine();
            if (Objects.isNull(line)) {
                break;
            }
            System.out.println(line);
        }
        OutputStream outputStream = accept.getOutputStream();
        String s = "HTTP/1.1 200 OK" + " /n " +
                "return  socket response";
        outputStream.write(s.getBytes(StandardCharsets.UTF_8),0,s.length());
        outputStream.close();
    }
}
