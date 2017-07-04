package com.hcb.xigou.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.iflytek.cloud.speech.LexiconListener;
import com.iflytek.cloud.speech.RecognizerListener;
import com.iflytek.cloud.speech.RecognizerResult;
import com.iflytek.cloud.speech.Setting;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechRecognizer;
import com.iflytek.cloud.speech.SpeechSynthesizer;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.cloud.speech.SynthesizeToUriListener;
import com.iflytek.cloud.speech.UserWords;

public class MscTest {

	private static final String APPID = "58635133";

	private static final String USER_WORDS = "{\"userword\":[{\"name\":\"计算机词汇\",\"words\":[\"随机存储器\",\"只读存储器\",\"扩充数据输出\",\"局部总线\",\"压缩光盘\",\"十七寸显示器\"]},{\"name\":\"我的词汇\",\"words\":[\"槐花树老街\",\"王小贰\",\"发炎\",\"公事\"]}]}";

	private static StringBuffer mResult = new StringBuffer();
	
	public static void main(String args[]) {
		
		new MscTest().getMscObj("http://living-2016.oss-cn-hangzhou.aliyuncs.com/c89f446a295c227de2e59cb1b6fa20ca.wav");
	}

	public String getMscObj(String url) {
		
		try {
			Setting.setShowLog(true);
			SpeechUtility.createUtility("appid=" + APPID);
			@SuppressWarnings("static-access")
			String result = new MscTest().Recognize(url);
			System.out.println("+++++++"+result);
			return result;
		} catch (Exception e) {
			return null;
		}finally {
			mResult.delete(0, mResult.length());
		}
	}

	public static void onLoop() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// *************************************音频流听写*************************************

	/**
	 * 听写
	 */
	public static String Recognize(String url) {
		if (SpeechRecognizer.getRecognizer() == null)
			SpeechRecognizer.createRecognizer();
		return RecognizePcmfileByte(url);
		
	}

	/**
	 * 自动化测试注意要点 如果直接从音频文件识别，需要模拟真实的音速，防止音频队列的堵塞
	 */
	public static String RecognizePcmfileByte(String url) {
		// 1、读取音频文件
		InputStream fis = null;
		byte[] voiceBuffer = null;
		ByteArrayOutputStream swapStream = null;
		byte[] buffer = new byte[1024];
		int hasRead = 0;
		try {
			swapStream = new ByteArrayOutputStream();
			fis = SimpleGetObjectSample.getUrlStream(url);
			 while ((hasRead = fis.read(buffer, 0, 100)) > 0) {  
		            swapStream.write(buffer, 0, hasRead);  
		        }  
		    voiceBuffer = swapStream.toByteArray();  
			//voiceBuffer = new byte[fis.available()];
			//fis.read(voiceBuffer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fis) {
					fis.close();
					fis = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 2、音频流听写
		if (0 == voiceBuffer.length) {
			mResult.append("no audio avaible!");
			return mResult.toString();
		} else {
			SpeechRecognizer recognizer = SpeechRecognizer.getRecognizer();
			recognizer.setParameter(SpeechConstant.DOMAIN, "iat");
			recognizer.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
			recognizer.setParameter(SpeechConstant.AUDIO_SOURCE, "-1");
			//写音频流时，文件是应用层已有的，不必再保存
//			recognizer.setParameter(SpeechConstant.ASR_AUDIO_PATH,
//					"./iflytek.pcm");
			recognizer.setParameter( SpeechConstant.RESULT_TYPE, "plain" );
			recognizer.startListening(recListener);
			ArrayList<byte[]> buffers = splitBuffer(voiceBuffer,
					voiceBuffer.length, 2000);
			for (int i = 0; i < buffers.size(); i++) {
				// 每次写入msc数据4.8K,相当150ms录音数据
				recognizer.writeAudio(buffers.get(i), 0, buffers.get(i).length);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			recognizer.stopListening();
			return mResult.toString();
		}
	}

	/**
	 * 将字节缓冲区按照固定大小进行分割成数组
	 * 
	 * @param buffer
	 *            缓冲区
	 * @param length
	 *            缓冲区大小
	 * @param spsize
	 *            切割块大小
	 * @return
	 */
	public static ArrayList<byte[]> splitBuffer(byte[] buffer, int length, int spsize) {
		ArrayList<byte[]> array = new ArrayList<byte[]>();
		if (spsize <= 0 || length <= 0 || buffer == null
				|| buffer.length < length)
			return array;
		int size = 0;
		while (size < length) {
			int left = length - size;
			if (spsize < left) {
				byte[] sdata = new byte[spsize];
				System.arraycopy(buffer, size, sdata, 0, spsize);
				array.add(sdata);
				size += spsize;
			} else {
				byte[] sdata = new byte[left];
				System.arraycopy(buffer, size, sdata, 0, left);
				array.add(sdata);
				size += left;
			}
		}
		return array;
	}

	/**
	 * 听写监听器
	 */
	public static RecognizerListener recListener = new RecognizerListener() {

		public void onBeginOfSpeech() {
			
		}

		public void onEndOfSpeech() {
			
		}

		public void onVolumeChanged(int volume) {
		
		}

		public void onResult(RecognizerResult result, boolean islast) {
			mResult.append(result.getResultString());
			if( islast ){
				DebugLog.Log("识别结果为:" + mResult.toString());
			}
		}

		public void onError(SpeechError error) {
			
		}

		public void onEvent(int eventType, int arg1, int agr2, String msg) {
			
		}

	};

	// *************************************无声合成*************************************

	/**
	 * 合成
	 */
	private void Synthesize() {
		SpeechSynthesizer speechSynthesizer = SpeechSynthesizer
				.createSynthesizer();
		// 设置发音人
		speechSynthesizer.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
		// 设置语速，范围0~100
		speechSynthesizer.setParameter(SpeechConstant.SPEED, "50");
		// 设置语调，范围0~100
		speechSynthesizer.setParameter(SpeechConstant.PITCH, "50");
		// 设置音量，范围0~100
		speechSynthesizer.setParameter(SpeechConstant.VOLUME, "50");
		// 设置合成音频保存位置（可自定义保存位置），默认保存在“./iflytek.pcm”
		speechSynthesizer.synthesizeToUri("科大讯飞语音合成测试程序 ", "./tts_test.pcm",
				synthesizeToUriListener);
	}

	/**
	 * 合成监听器
	 */
	SynthesizeToUriListener synthesizeToUriListener = new SynthesizeToUriListener() {

		public void onBufferProgress(int progress) {
			DebugLog.Log("*************合成进度*************" + progress);

		}

		public void onSynthesizeCompleted(String uri, SpeechError error) {
			if (error == null) {
				DebugLog.Log("*************合成成功*************");
				DebugLog.Log("合成音频生成路径：" + uri);
			} else
				DebugLog.Log("*************" + error.getErrorCode()
						+ "*************");
			onLoop();

		}

	};

	// *************************************词表上传*************************************

	/**
	 * 词表上传
	 */
	private void uploadUserWords() {
		SpeechRecognizer recognizer = SpeechRecognizer.getRecognizer();
		if ( recognizer == null) {
			recognizer = SpeechRecognizer.createRecognizer();
			
			if( null == recognizer ){
				DebugLog.Log( "获取识别实例实败！" );
				onLoop();
				return;
			}
		}

		UserWords userwords = new UserWords(USER_WORDS);
		recognizer.setParameter( SpeechConstant.DATA_TYPE, "userword" );
		recognizer.updateLexicon("userwords",
				userwords.toString(), 
				lexiconListener);
	}

	/**
	 * 词表上传监听器
	 */
	LexiconListener lexiconListener = new LexiconListener() {
		@Override
		public void onLexiconUpdated(String lexiconId, SpeechError error) {
			if (error == null)
				DebugLog.Log("*************上传成功*************");
			else
				DebugLog.Log("*************" + error.getErrorCode()
						+ "*************");
			onLoop();
		}

	};
}
