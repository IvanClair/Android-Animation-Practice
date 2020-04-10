package personal.ivan.animationpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import personal.ivan.animationpractice.databinding.Activity2Binding

class Activity2 : AppCompatActivity() {

    companion object {
        const val T_NAME = "T_NAME"
    }

    private val mBinding: Activity2Binding by lazy {
        Activity2Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        mBinding.textView1.transitionName = T_NAME
    }
}