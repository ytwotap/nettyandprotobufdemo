package com.yt.capter.capter_6_task_excute.siggle_task;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/**
 * 在线程中执行任务
 * @author: YT
 * @date: 2022/1/21/021
 */
public class Demo2 {
    @Test
    public void startSocket() throws IOException {
        ServerSocket socket = new ServerSocket(8088);
        while (true) {
            Socket accept = socket.accept();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        handelerRequest(accept);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
        new Thread(runnable).start();
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
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write(" return  socket resonse" );
        bufferedWriter.close();
        outputStream.close();
    }
}
