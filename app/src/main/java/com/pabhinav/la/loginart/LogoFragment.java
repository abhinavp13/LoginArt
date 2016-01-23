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

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LogoFragment extends Fragment {

    /**
     * Renderer for animated logo
     */
    private AnimatedLogoView mAnimatedLogoView;

    /**
     * Initial little y axis translation offset
     */
    private float mInitialLogoOffset;

    /**
     * Called to do initial creation of a fragment.  This is called after
     * {@link #onAttach(Activity)} and before
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     *
     * <p>Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, see {@link #onActivityCreated(Bundle)}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInitialLogoOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     *
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mRootView = inflater.inflate(R.layout.animated_logo_fragment, container, false);

        mAnimatedLogoView = (AnimatedLogoView) mRootView.findViewById(R.id.animated_logo);
        mAnimatedLogoView.setOnStateChangeListener(new AnimatedLogoView.OnStateChangeListener() {
            @Override
            public void onStateChange(int state) {
                if (state == AnimatedLogoView.STATE_FILL_STARTED) {
                    // Bug in older versions where set.setInterpolator didn't work
                    AnimatorSet set = new AnimatorSet();
                    ObjectAnimator a1 = ObjectAnimator.ofFloat(mAnimatedLogoView, View.TRANSLATION_Y, 0);
                    set.setDuration(500).playTogether(a1);
                    set.start();
                }
            }
        });
        reset();
        return mRootView;
    }

    /**
     * Triggers logo animation
     */
    public void start() {
        mAnimatedLogoView.start();
    }

    /**
     * Resets logo animation.
     */
    public void reset() {
        mAnimatedLogoView.reset();
        mAnimatedLogoView.setTranslationY(mInitialLogoOffset);
    }
}
