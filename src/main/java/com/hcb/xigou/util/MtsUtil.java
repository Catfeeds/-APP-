package com.hcb.xigou.util;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.mts.model.v20140618.SubmitJobsRequest;
import com.aliyuncs.mts.model.v20140618.SubmitJobsResponse;
import com.aliyuncs.mts.model.v20140618.SubmitSnapshotJobRequest;
import com.aliyuncs.mts.model.v20140618.SubmitSnapshotJobResponse;
import com.aliyuncs.profile.DefaultProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dor on 16-12-28.
 */
public class MtsUtil {
    //    endPoint=http://oss-cn-hangzhou.aliyuncs.com
//    accessKeyId=QlqcNxHtTbnhXp5C
//    accessKeySecret=Ivm0fTUxJbFZ8s5KVX7Npf4hHbZAW5
//    bucketName=living-2016
    private final static String endPoint = "cn-hangzhou";
    private final static String location = "oss-cn-hangzhou";
    private final static String baseUrl = "http://cdnvoice.yaoguo1818.com/";
    private final static String accessKeyId = "LTAI3O81flgePvKu";
    private final static String accessKeySecret = "MU0IW130M5jaj0p67zMecCyjZhyxNS";
    private final static String bucketName = "voiceroom";
    //    免费的应该就一个
    private final static String pipeId = "9554bd70545b46d2bf073851b5e5494e";
  
    private static DefaultAcsClient client;

    static {
        DefaultProfile profile = DefaultProfile.getProfile(
                endPoint,
                accessKeyId,
                accessKeySecret);
        client = new DefaultAcsClient(profile);
    }
  
    public static void main(String[] args) throws ClientException {
        System.out.println(tranAudio("voice_1483706780386.wav"));
    }

    //预置模板 S00000001-300020 MP3-128
    public static String tranAudio(String fileName) throws ClientException {
        Map<String, String> map = new HashMap<>();
        String name = setFileName(fileName, ".mp3");
        map.put("OutputObject", name);
        map.put("TemplateId", "S00000001-300020");
        getSubmitJob(fileName, map);
        return baseUrl + name;
    }

    //预置模板 S00000001-000020 FLV-SD
    //预置模板 S00000001-100020 M3U8-SD
    public static String tranVedio(String fileName) throws ClientException {
        Map<String, String> map = new HashMap<>();
        String name = setFileName(fileName, ".flv");
        map.put("OutputObject", name);
        map.put("TemplateId", "S00000001-000020");
        getSubmitJob(fileName, map);
        return baseUrl + name;
    }

    public static SubmitSnapshotJobResponse getSnophot(String fileName) throws ClientException {
        SubmitSnapshotJobRequest request = new SubmitSnapshotJobRequest();
        request.setInput(getInput(fileName));
        request.setSnapshotConfig(fileName);
        request.setMethod(MethodType.GET);
        return client.getAcsResponse(request);
    }

    private static String setFileName(String fileName, String suffix) {
        if (null == fileName || "".equals(fileName)) {
            throw new RuntimeException("OSS error");
        }
        String[] split = fileName.split("\\.");
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < split.length - 1 ;i++) {
            name.append(split[i]);
        }
        return "compress/" + name + suffix;
    }

    private static SubmitJobsResponse getSubmitJob(String fileName, Map<String, String> out) throws ClientException {
        SubmitJobsRequest request = new SubmitJobsRequest();
        request.setInput(getInput(fileName));
        request.setOutputBucket(bucketName);
        List<Map<String, String>> list = new ArrayList<>();
        list.add(out);
        request.setOutputs(JSON.toJSONString(list));
        request.setPipelineId(pipeId);
        request.setMethod(MethodType.GET);
        return client.getAcsResponse(request);
    }

    private static String getInput(String fileName) {
        Map<String, String> map = new HashMap<>();
        map.put("Bucket", bucketName);
        map.put("Location", location);
        map.put("Object", fileName);
        return JSON.toJSONString(map);
    }
}