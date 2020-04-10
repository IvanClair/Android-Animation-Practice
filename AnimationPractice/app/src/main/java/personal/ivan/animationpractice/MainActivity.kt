package personal.ivan.animationpractice

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import personal.ivan.animationpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var mAnim: AnimationDrawable
    private lateinit var mAnimVector: AnimatedVectorDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        mBinding.button1.setOnClickListener { valueAlpha() }
        mBinding.button2.setOnClickListener { objectAlpha(endValue = 0f) }
        mBinding.button3.setOnClickListener { objectAlpha(endValue = 1f) }

        mBinding.button4.setOnClickListener { objectTranslationX(width = 700f) }
        mBinding.button5.setOnClickListener { objectTranslationX(width = 0f) }

        mBinding.button6.setOnClickListener {
            mBinding.imageView.visibility =
                when (mBinding.imageView.visibility) {
                    View.VISIBLE -> View.INVISIBLE
                    else -> View.VISIBLE
                }
        }

        mBinding.button8.setOnClickListener { keyFrame() }

        mBinding.button9.setOnClickListener { playTogether() }

        mBinding.imageView2.apply {
            mAnimVector = drawable as AnimatedVectorDrawable
//            setBackgroundResource(R.drawable.anim_move)
//            mAnim = background as AnimationDrawable
        }
        mBinding.button10.setOnClickListener { mAnimVector.start() }
    }

    private fun valueAlpha() {
        ValueAnimator.ofFloat(1f, 0f).apply {
            duration = 2000
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            start()
            addUpdateListener {
                mBinding.imageView.alpha = it.animatedValue as Float
            }
        }
    }

    private fun objectAlpha(endValue: Float) {
        ObjectAnimator
            .ofFloat(mBinding.imageView, "alpha", endValue)
            .apply {
                duration = 2000
                start()
            }
    }

    private fun objectTranslationX(width: Float) {
        ObjectAnimator
            .ofFloat(mBinding.imageView, "translationX", width)
            .apply {
                duration = 1000
                interpolator = OvershootInterpolator()
                start()
            }
    }

    private fun keyFrame() {
        val kf0 = Keyframe.ofFloat(0f, 0f)
        val kf1 = Keyframe.ofFloat(.5f, 360f)
        val kf2 = Keyframe.ofFloat(1f, 0f)
        val pvhRotation =
            PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2)
        ObjectAnimator.ofPropertyValuesHolder(mBinding.imageView, pvhRotation).apply {
            duration = 5000
            start()
        }
    }

    private fun playTogether() {
//        val pvhX = PropertyValuesHolder.ofFloat("x", 50f)
//        val pvhY = PropertyValuesHolder.ofFloat("y", 100f)
//        ObjectAnimator.ofPropertyValuesHolder(mBinding.imageView, pvhX, pvhY).start()

        mBinding.imageView
            .animate()
            .setDuration(2000)
            .rotation(180f)
            .setInterpolator(OvershootInterpolator())
            .x(600f)
    }
}