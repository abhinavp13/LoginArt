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

import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BaseInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * This class is helper class for showing translate animations on a given
 * {@link View} object.
 * <p />
 * It has callback interface implementation, which calls the implementing
 * class when translate animation ends.
 *
 * @author Abhinav Puri (pabhinav@iitrpr.ac.in)
 */
public class TranslateAnimationHelper implements Animation.AnimationListener {

    /**
     * Animation translation parameters
     */
    private float mFromXDelta, mToXDelta, mFromYDelta, mToYDelta;

    /**
     * Animation translation properties
     */
    private long mDuration, mStartOffSet;
    private BaseInterpolator mInterpolator;

    /**
     * Callback interface for notifying end of translate animation.
     */
    private TranslateAnimationEndListener mTranslateAnimationEndListener;

    /**
     * Default duration for translate animation.
     */
    private static final long DEFAULT_DURATION = 500;

    /**
     * {@link View} object on which translate animation will be done.
     */
    private View mViewToAnimate;

    /**
     * Constructor to use when building a TranslateAnimation from code
     *
     * @param viewToAnimate View on which translate animation will be performed
     * @param fromXDelta Change in X coordinate to apply at the start of the
     *        animation
     * @param toXDelta Change in X coordinate to apply at the end of the
     *        animation
     * @param fromYDelta Change in Y coordinate to apply at the start of the
     *        animation
     * @param toYDelta Change in Y coordinate to apply at the end of the
     *        animation
     */
    public TranslateAnimationHelper(@NonNull View viewToAnimate, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta){
        init(viewToAnimate, fromXDelta, toXDelta, fromYDelta, toYDelta, DEFAULT_DURATION, 0, null);
        doTranslateAnimation();
    }

    /**
     * Constructor to use when building a TranslateAnimation from code
     *
     * @param viewToAnimate View on which translate animation will be performed
     * @param fromXDelta Change in X coordinate to apply at the start of the
     *        animation
     * @param toXDelta Change in X coordinate to apply at the end of the
     *        animation
     * @param fromYDelta Change in Y coordinate to apply at the start of the
     *        animation
     * @param toYDelta Change in Y coordinate to apply at the end of the
     *        animation
     * @param interpolator Sets the acceleration curve for this animation.
     */
    public TranslateAnimationHelper(@NonNull View viewToAnimate, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta, BaseInterpolator interpolator){
        init(viewToAnimate, fromXDelta, toXDelta, fromYDelta, toYDelta, DEFAULT_DURATION, 0, interpolator);
        doTranslateAnimation();
    }

    /**
     * Constructor to use when building a TranslateAnimation from code
     *
     * @param viewToAnimate View on which translate animation will be performed
     * @param fromXDelta Change in X coordinate to apply at the start of the
     *        animation
     * @param toXDelta Change in X coordinate to apply at the end of the
     *        animation
     * @param fromYDelta Change in Y coordinate to apply at the start of the
     *        animation
     * @param toYDelta Change in Y coordinate to apply at the end of the
     *        animation
     * @param startOffSet When this Animation should start, in milliseconds from
     *                    the start time of the root AnimationSet.
     * @param interpolator Sets the acceleration curve for this animation.
     */
    public TranslateAnimationHelper(@NonNull View viewToAnimate, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta, long startOffSet, BaseInterpolator interpolator){
        init(viewToAnimate, fromXDelta, toXDelta, fromYDelta, toYDelta, DEFAULT_DURATION, startOffSet, interpolator);
        doTranslateAnimation();
    }

    /**
     * Constructor to use when building a TranslateAnimation from code
     *
     * @param viewToAnimate View on which translate animation will be performed
     * @param fromXDelta Change in X coordinate to apply at the start of the
     *        animation
     * @param toXDelta Change in X coordinate to apply at the end of the
     *        animation
     * @param fromYDelta Change in Y coordinate to apply at the start of the
     *        animation
     * @param toYDelta Change in Y coordinate to apply at the end of the
     *        animation
     * @param startOffSet When this Animation should start, in milliseconds from
     *                    the start time of the root AnimationSet.
     * @param duration Duration in milliseconds.
     */
    public TranslateAnimationHelper(@NonNull View viewToAnimate, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta, long duration, long startOffSet){
        init(viewToAnimate, fromXDelta, toXDelta, fromYDelta, toYDelta, duration, startOffSet, null);
        doTranslateAnimation();
    }

    /**
     * Constructor to use when building a TranslateAnimation from code
     *
     * @param viewToAnimate View on which translate animation will be performed
     * @param fromXDelta Change in X coordinate to apply at the start of the
     *        animation
     * @param toXDelta Change in X coordinate to apply at the end of the
     *        animation
     * @param fromYDelta Change in Y coordinate to apply at the start of the
     *        animation
     * @param toYDelta Change in Y coordinate to apply at the end of the
     *        animation
     * @param duration Duration in milliseconds.
     * @param startOffSet When this Animation should start, in milliseconds from
     *                    the start time of the root AnimationSet.
     * @param interpolator Sets the acceleration curve for this animation.
     */
    public TranslateAnimationHelper(@NonNull View viewToAnimate, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta, long duration, long startOffSet, BaseInterpolator interpolator){
        init(viewToAnimate, fromXDelta, toXDelta, fromYDelta, toYDelta, duration, startOffSet, interpolator);
        doTranslateAnimation();
    }

    /**
     * Initialize parameters and properties for TranslateAnimation.
     *
     * @param viewToAnimate View on which translate animation will be performed
     * @param fromXDelta Change in X coordinate to apply at the start of the
     *        animation
     * @param toXDelta Change in X coordinate to apply at the end of the
     *        animation
     * @param fromYDelta Change in Y coordinate to apply at the start of the
     *        animation
     * @param toYDelta Change in Y coordinate to apply at the end of the
     *        animation
     * @param duration Duration in milliseconds.
     * @param startOffSet When this Animation should start, in milliseconds from
     *                    the start time of the root AnimationSet.
     * @param interpolator Sets the acceleration curve for this animation.
     */
    public void init(@NonNull View viewToAnimate, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta, long duration, long startOffSet, BaseInterpolator interpolator){
        mFromXDelta = fromXDelta;
        mToXDelta = toXDelta;
        mFromYDelta = fromYDelta;
        mToYDelta = toYDelta;
        mDuration = duration;
        mStartOffSet = startOffSet;
        mInterpolator = interpolator;
        mViewToAnimate = viewToAnimate;
    }

    /**
     * <p>Notifies the start of the animation.</p>
     *
     * @param animation The started animation.
     */
    @Override
    public void onAnimationStart(Animation animation) {}

    /**
     * <p>Notifies the end of the animation. This callback is not invoked
     * for animations with repeat count set to INFINITE.</p>
     *
     * @param animation The animation which reached its end.
     */
    @Override
    public void onAnimationEnd(Animation animation) {
        mTranslateAnimationEndListener.onTranslateAnimationEnd();
    }

    /**
     * <p>Notifies the repetition of the animation.</p>
     *
     * @param animation The animation which was repeated.
     */
    @Override
    public void onAnimationRepeat(Animation animation) {}

    /**
     * Interface used as callback for notifying end of translate animation
     */
    interface TranslateAnimationEndListener {

        /**
         * Method invoked on end of translate animation.
         */
        void onTranslateAnimationEnd();
    }

    /**
     * Setter method for {@link com.pabhinav.la.loginart.TranslateAnimationHelper.TranslateAnimationEndListener} interface.
     *
     * @param translateAnimationEndListener This is used as callback interface for notifying end of animation.
     */
    public void setTranslateAnimationEndListener(TranslateAnimationEndListener translateAnimationEndListener){
        mTranslateAnimationEndListener = translateAnimationEndListener;
    }

    /**
     * This method does translate animation on the given view.
     * <p />
     * It uses animation parameters to initialize translate animation object.
     * Then, it sets animation properties to the initialized translate animation object.
     * Finally, starts the animation.
     */
    private void doTranslateAnimation(){

        TranslateAnimation translateAnimation = new TranslateAnimation(mFromXDelta, mToXDelta, mFromYDelta, mToYDelta);
        translateAnimation.setStartOffset(mStartOffSet);
        translateAnimation.setDuration(mDuration);
        translateAnimation.setFillAfter(true);
        if(mInterpolator != null) {
            translateAnimation.setInterpolator(mInterpolator);
        }
        translateAnimation.setAnimationListener(this);

        mViewToAnimate.startAnimation(translateAnimation);
    }
}
