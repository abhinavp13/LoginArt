/*
 * Copyright (C) 2016 Abhinav Puri
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pabhinav.la.loginart;

import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import at.markushi.ui.RevealColorView;

/**
 * This is the main activity for this app.
 * <p />
 * It is just a showcase for login/register feature, can be used in any app.
 * It makes use of a lot of collaboration of lot of Animations available in
 * android sdk. Also, it uses Scene Transitions made available in android
 * from kitkat version.
 * <p />
 * Also, used open source library from markushi, reference :
 * https://github.com/markushi/android-ui
 * <p />
 *
 * @author Abhinav Puri (pabhinav@iitrpr.ac.in)
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * {@link TransitionHelper} class object used to help do scene transition
     */
    private TransitionHelper mTransitionHelper;

    /**
     * {@link ImageButton} object used to perform translate animations.
     */
    private ImageButton mCircularAnimatingView;

    /**
     * {@link Toast} object used by this activity.
     */
    private Toast mToast;

    /**
     * Scale Animation duration
     */
    private static final long SCALE_ANIMATION_DURATION = 800;

    /**
     * Scale Animation starting offset
     */
    private static final long SCALE_ANIMATION_STARTOFFSET = 2500;

    /**
     * Animated logo starting offset
     */
    private static final long ANIMATED_LOGO_STARTOFFSET = 1000;

    /**
     * Translate Animation starting offset
     */
    private static final long TRANSLATE_TO_BOTTOM_START_OFFSET = SCALE_ANIMATION_STARTOFFSET + SCALE_ANIMATION_DURATION + 100;

    /**
     * Reveal Animation starting offset
     */
    private static final long REVEAL_ANIMATION_START_OFFSET = TRANSLATE_TO_BOTTOM_START_OFFSET + 500;

    /**
     * Called when the activity is starting.
     * <p />
     * This is where most initialization should go:
     * calling {@link #setContentView(int)} to inflate the activity's UI,
     * using {@link #findViewById} to programmatically interact with widgets
     * in the UI.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        /**
         * Extend layout to status bar,
         * <p />
         * Complete list of items done to achieve this :
         * <p />
         * Added 'Flag_layout_no_limits' flag to window.
         * Added translucent behaviour (along with some other
         * properties) can be checked at values-v21/styles,
         * Also, important, add 'android:fitsSystemWindows="true"' behaviour to the main
         * container layout.
         */
        extendImageToDrawOverStatusBar();

        /** Initialize local variables **/
        mTransitionHelper = new TransitionHelper();
        mCircularAnimatingView = (ImageButton)findViewById(R.id.scene_transition_between_elements_go_button_and_login_register_tile);

        /** Its time to first setup animated logo **/
        setupAnimatedLogo();

        /** Then, bouncing ball needs to go visible on screen **/
        popOutBall();

        /** Now, need to setup bouncing animation of bouncing ball **/
        setupBouncingOfBall();

        /** Finally, setup reveal color animation **/
        setupRevealEffect();
    }

    /**
     * Initializes animated logo fragment for {@link android.graphics.DashPathEffect} animation.
     * It uses svg glyph data to fetch {@link android.graphics.Path} object, which helps
     * render logo text on screen.
     */
    public void setupAnimatedLogo(){

        /** Animated Logo Fragment initialization **/
        final LogoFragment logoFragment = (LogoFragment) getFragmentManager().findFragmentById(R.id.animated_logo_fragment);
        logoFragment.reset();
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                /** Start the animation **/
                logoFragment.start();
            }
        }, ANIMATED_LOGO_STARTOFFSET);
    }

    /**
     * Sleep for few seconds, and then show reveal animation.
     * <p />
     * Its timer is set in such a way that reveal is shown only when circular animating view
     * reaches bottom of screen.
     */
    public void setupRevealEffect(){

        /** Does reveal animation after some start offset duration **/
        final Handler handler = new Handler();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        reveal((RevealColorView) findViewById(R.id.reveal), findViewById(R.id.dummy_view_for_revealing_animation));
                    }
                });
            }
        }, REVEAL_ANIMATION_START_OFFSET);
    }

    /**
     * First it makes ball invisible.
     * Then, slowly scales ball from 0.0 to 1.0 scale, using {@link ObjectAnimator} scale property.
     */
    public void popOutBall(){

        /** Hide bouncing ball **/
        mCircularAnimatingView.setScaleX(0.0f);
        mCircularAnimatingView.setScaleY(0.0f);

        /** Pop out animating ball with scale animation **/
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(mCircularAnimatingView,"scaleX", 0.0f, 1.0f);
        scaleXAnimation.setDuration(SCALE_ANIMATION_DURATION);
        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(mCircularAnimatingView,"scaleY", 0.0f, 1.0f);
        scaleYAnimation.setDuration(SCALE_ANIMATION_DURATION);

        /** Start scaling animation **/
        scaleXAnimation.setStartDelay(SCALE_ANIMATION_STARTOFFSET);
        scaleYAnimation.setStartDelay(SCALE_ANIMATION_STARTOFFSET);
        scaleYAnimation.start();
        scaleXAnimation.start();

        /** Show bouncing ball **/
        final Handler handler = new Handler();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        mCircularAnimatingView.setVisibility(View.VISIBLE);
                    }
                });
            }
        }, SCALE_ANIMATION_STARTOFFSET);
    }

    /**
     * Bouncing Ball translation animation.
     * This first makes the ball goes from center to bottom to screen,
     * then, makes ball come back from bottom to center of screen.
     */
    public void setupBouncingOfBall(){

        /** Accelerate ball to bottom **/
        TranslateAnimationHelper translateToBottom = new TranslateAnimationHelper(mCircularAnimatingView, 0f, 0f, 0f, getWindowManager().getDefaultDisplay().getHeight()/2.2f, TRANSLATE_TO_BOTTOM_START_OFFSET, new AccelerateInterpolator());

        translateToBottom.setTranslateAnimationEndListener(new TranslateAnimationHelper.TranslateAnimationEndListener() {

            @Override
            public void onTranslateAnimationEnd() {

                /** De-accelerate ball back to original position **/
                TranslateAnimationHelper backToPositionTranslateAnimation = new TranslateAnimationHelper(mCircularAnimatingView, 0f, 0f, getWindowManager().getDefaultDisplay().getHeight() / 2.2f, 0f, new DecelerateInterpolator());

                backToPositionTranslateAnimation.setTranslateAnimationEndListener(new TranslateAnimationHelper.TranslateAnimationEndListener() {

                    @Override
                    public void onTranslateAnimationEnd() {

                        mCircularAnimatingView.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_forward_white_24dp));
                        mCircularAnimatingView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                goClicked(v);
                            }
                        });
                    }
                });
            }
        });
    }


    /**
     * Sets the color of status bar.
     * <p />
     * Since, setting status bar color requires api version >= KITKAT,
     * an important version check is inside this method.
     */
    public void extendImageToDrawOverStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    /**
     * This function gets triggered whenever register fab icon is clicked,
     * Does simple scene transition to register scene.
     *
     * @param v {@link android.support.design.widget.FloatingActionButton} with
     *          user addition icon is the view passed to this function.
     */
    public void registerClicked(View v){
        mTransitionHelper.doSceneTransition(R.id.root_container, R.layout.register_scene_3, LoginActivity.this);
    }

    /**
     * This function gets triggered whenever cross button present on register ui,
     * is pressed by user.
     * Does simple scene transition to login scene.
     *
     * @param v {@link android.support.design.widget.FloatingActionButton} with
     *          cross icon is the view passed to this function.
     */
    public void crossClicked(View v){
        mTransitionHelper.doSceneTransition(R.id.root_container, R.layout.login_scene_2, LoginActivity.this);
    }

    /**
     * This function gets triggered whenever presses forward arrow button present on main
     * ui to reach login/register ui screen.
     *
     * @param v View with forward arrow, leading to login/register ui screen.
     */
    public void goClicked(View v){
        mTransitionHelper.doSceneTransition(R.id.root_container, R.layout.login_scene_2, LoginActivity.this);
    }

    /**
     * This method returns the x,y coordinates of the point from where
     * reveal animation will get started.
     *
     * @param src Reveal animation view, on this view reveal animation
     *            will be triggered.
     * @param target This is the view, which helps find the center of
     *               reveal animation view, located at bottom of screen.
     * @return {@link Point} object whose coordinates are going to be
     *         center for reveal effect.
     */
    private Point getLocationInView(View src, View target) {

        final int[] l0 = new int[2];
        src.getLocationOnScreen(l0);

        final int[] l1 = new int[2];
        target.getLocationOnScreen(l1);

        l1[0] = l1[0] - l0[0] + target.getWidth() / 2;
        l1[1] = l1[1] - l0[1] + target.getHeight() / 2;

        return new Point(l1[0], l1[1]);
    }

    /**
     * Given the view, it calculates the center of reveal animation,
     * and starts reveal animation.
     *
     * @param v This is the view, which helps find the center of
     *          reveal animation view, located at bottom of screen.
     */
    public void reveal(RevealColorView revealColorView, View v){
        final Point p = getLocationInView(revealColorView, v);
        revealColorView.reveal(p.x, p.y, getResources().getColor(R.color.colorPrimaryDark), v.getHeight() / 2, 500, null);
    }

    /**
     * This function gets triggered whenever submit button is clicked,
     * Clears previously created {@link Toast} object, and creates a
     * new {@link Toast} object and displays on screen.
     *
     * @param v submit button view object
     */
    public void submitClicked(View v){

        if(mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(LoginActivity.this,"This is a demo login/register ui", Toast.LENGTH_SHORT);
        mToast.show();
    }
}
