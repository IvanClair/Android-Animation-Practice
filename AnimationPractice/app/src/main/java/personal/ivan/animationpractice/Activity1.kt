package personal.ivan.animationpractice

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.util.Pair
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import personal.ivan.animationpractice.databinding.Activity1Binding


class Activity1 : AppCompatActivity() {

    private val mBinding: Activity1Binding by lazy {
        Activity1Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        with(window) {
//            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
//
//            // set an exit transition
//            exitTransition = Explode()
//        }
        setContentView(mBinding.root)

        mBinding.button1.setOnClickListener {
            // inside your activity (if you did not enable transitions in your theme)
            val option =
                ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    Pair(mBinding.textView1, Activity2.T_NAME)
                )
            startActivity(
                Intent(this, Activity2::class.java),
                option.toBundle()
            )
        }
    }
}