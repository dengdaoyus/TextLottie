package cn.lottie.textlottie;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.model.LottieComposition;

import static android.R.id.button1;
import static android.R.id.button2;
import static cn.lottie.textlottie.R.id.animation_view;

public class MainActivity extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    private Button button1,button2;
    private TextView tv_seek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         lottieAnimationView= (LottieAnimationView) findViewById(R.id.animation_view);

        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        tv_seek=(TextView)findViewById(R.id.tv_seek);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startAnima();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stopAnima();
            }
        });
        LottieComposition.fromAssetFileName(this, "12.22.json", new LottieComposition.OnCompositionLoadedListener() {

            @Override
            public void onCompositionLoaded(LottieComposition composition) {
                lottieAnimationView.setComposition(composition);
                lottieAnimationView.setProgress(0.333f);
                lottieAnimationView.playAnimation();
            }
        });


        lottieAnimationView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tv_seek.setText(" 动画进度" +(int) (animation.getAnimatedFraction()*100) +"%");
            }
        });

    }

    /*
    * 开始动画
    */
    private  void startAnima(){

        boolean inPlaying = lottieAnimationView.isAnimating();
        if (!inPlaying) {
            lottieAnimationView.setProgress(0f);
            lottieAnimationView.playAnimation();
        }
    }
    /*
    * 停止动画
    */
    private  void stopAnima(){
        boolean inPlaying = lottieAnimationView.isAnimating();
        if (inPlaying) {
            lottieAnimationView.cancelAnimation();
        }
    }
}
