package com.zbar.lib;

import android.os.Handler;
import android.os.Message;

import com.easemob.easeui.R;

/**
 * 作者: 陈涛(1076559197@qq.com)
 * 
 * 时间: 2014年5月9日 下午12:23:32
 * 
 * 版本: V_1.0.0
 * 
 * 描述: 扫描消息转发
 */
public final class CaptureActivityHandler extends Handler {

	DecodeThread decodeThread = null;
	CaptureActivity activity = null;
	private State state;

	private enum State {
		PREVIEW, SUCCESS, DONE
	}

	public CaptureActivityHandler(CaptureActivity activity) {
		this.activity = activity;
		decodeThread = new DecodeThread(activity);
		decodeThread.start();
		state = State.SUCCESS;
		CameraManager.get().startPreview();
		restartPreviewAndDecode();
	}

	@Override
	public void handleMessage(Message message) {

		switch (message.what) {
		case ConstantErWeiMa.auto_focus:
			if (state == State.PREVIEW) {
				CameraManager.get().requestAutoFocus(this,
						ConstantErWeiMa.auto_focus);
			}
			break;
		case ConstantErWeiMa.restart_preview:
			restartPreviewAndDecode();
			break;
		case ConstantErWeiMa.decode_succeeded:
			state = State.SUCCESS;
			activity.handleDecode((String) message.obj);// 解析成功，回调
			break;

		case ConstantErWeiMa.decode_failed:
			state = State.PREVIEW;
			CameraManager.get().requestPreviewFrame(decodeThread.getHandler(),
					ConstantErWeiMa.decode);
			break;
		}

	}

	public void quitSynchronously() {
		state = State.DONE;
		CameraManager.get().stopPreview();
		removeMessages(ConstantErWeiMa.decode_succeeded);
		removeMessages(ConstantErWeiMa.decode_failed);
		removeMessages(ConstantErWeiMa.decode);
		removeMessages(ConstantErWeiMa.auto_focus);
	}

	private void restartPreviewAndDecode() {
		if (state == State.SUCCESS) {
			state = State.PREVIEW;
			CameraManager.get().requestPreviewFrame(decodeThread.getHandler(),
					ConstantErWeiMa.decode);
			CameraManager.get().requestAutoFocus(this,
					ConstantErWeiMa.auto_focus);
		}
	}

}
