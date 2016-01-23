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

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.ViewGroup;

/**
 * This is helper class for doing scene transitions,
 * Uses {@link TransitionManager} for this purpose.
 *
 * @author Abhinav Puri (pabhinav@iitrpr.ac.in)
 */
public class TransitionHelper {

    /**
     * Root Container Id, using which transition will be done.
     */
    private int mRootScene;

    /**
     * Layout id, which when inflated which be used to create a
     * scene, to which transition will be done.
     */
    private int mSceneToTransitTo;

    /**
     * Activity Context element, consumed by {@link TransitionManager}.
     */
    private Context mContext;

    /**
     * This is the only public method, which can be called to set important
     * elements required for performing a scene transition.
     * <p />
     * It not only sets those elements, but also performs scene transition.
     *
     * @param rootScene The root container id, on which transition will be done.
     * @param sceneToTransitTo The layout id, which will be inflated to create
     *                         the scene.
     * @param context Activity context object, consumed by {@link TransitionManager}.
     */
    public void doSceneTransition(int rootScene, int sceneToTransitTo, @NonNull Context context){
        mRootScene = rootScene;
        mSceneToTransitTo = sceneToTransitTo;
        mContext = context;

        /** Call for scene transition **/
        doTransition();
    }

    /**
     * This method does scene transition requires root container,
     * a layout to transit to, and activity context.
     */
    private void doTransition(){
        TransitionManager.go(Scene.getSceneForLayout(getViewGroupFromRootScene(), mSceneToTransitTo, mContext));
    }

    /**
     * Finds a view that was identified by the id attribute from the XML.
     * Converts it to {@link ViewGroup} and returns it.
     *
     * @return The root container {@link ViewGroup}, if found or null otherwise.
     */
    private ViewGroup getViewGroupFromRootScene(){
        return (ViewGroup)((Activity)mContext).findViewById(mRootScene);
    }
}
