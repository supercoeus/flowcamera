package com.hbzhou.open.CameraView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ToastUtils
import com.hbzhou.open.flowcamera.FlowCameraView
import com.hbzhou.open.flowcamera.listener.FlowCameraListener
import com.hbzhou.open.flowcamera.util.LogUtil
import java.io.File


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val flowCamera = findViewById<FlowCameraView>(R.id.flowCamera)
        // 绑定生命周期 您就不用关心Camera的开启和关闭了 不绑定无法预览
        flowCamera.setBindToLifecycle(this)
        // 设置最大可拍摄小视频时长
        flowCamera.setRecordVideoMaxTime(10)
        // 设置拍照或拍视频回调监听
        flowCamera.setFlowCameraListener(object : FlowCameraListener {
            // 录制完成视频文件返回
            override fun recordSuccess(file: File) {
                ToastUtils.showLong(file.absolutePath)
                finish()
            }

            // 操作拍照或录视频出错
            override fun onError(videoCaptureError: Int, message: String, cause: Throwable?) {
                LogUtil.e(videoCaptureError.toString().plus("----").plus(message).plus("---").plus(cause.toString()))
            }

            // 拍照返回
            override fun captureSuccess(file: File) {
                ToastUtils.showLong(file.absolutePath)
                finish()
            }
        })
        //左边按钮点击事件
        flowCamera.setLeftClickListener {
            finish()
        }
    }
}
