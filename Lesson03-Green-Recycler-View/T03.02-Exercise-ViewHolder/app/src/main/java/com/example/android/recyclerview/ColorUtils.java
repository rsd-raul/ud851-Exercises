/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

/**
 * ColorUtils is a class with one method, used to color the ViewHolders in
 * the RecyclerView. I put in a separate class in an attempt to keep the
 * code organized.
 *
 * We aren't going to go into detail about how this method works, but feel
 * free to explore!
 */
public class ColorUtils {

    /**
     * This method returns the appropriate shade of green to form the gradient
     * seen in the list, based off of the order in which the
     * {@link com.example.android.recyclerview.GreenAdapter.NumberViewHolder}
     * instance was created.
     *
     * This method is used to show how ViewHolders are recycled in a RecyclerView.
     * At first, the colors will form a nice, consistent gradient. As the
     * RecyclerView is scrolled, the
     * {@link com.example.android.recyclerview.GreenAdapter.NumberViewHolder}'s will be
     * recycled and the list will no longer appear as a consistent gradient.
     *
     * @param context     Context for getting colors
     * @param instanceNum Order in which the calling ViewHolder was created
     *
     * @return A shade of green based off of when the calling ViewHolder
     * was created.
     */
    public static int getViewHolderBackgroundColorFromInstance(Context context, int instanceNum) {
        int shades[] = {R.color.material50Green, R.color.material100Green, R.color.material150Green,
                R.color.material200Green, R.color.material250Green, R.color.material300Green,
                R.color.material350Green, R.color.material400Green, R.color.material450Green,
                R.color.material500Green, R.color.material550Green, R.color.material600Green,
                R.color.material650Green, R.color.material700Green, R.color.material750Green,
                R.color.material800Green, R.color.material850Green, R.color.material900Green};

        int color = Color.WHITE;
        if(instanceNum >= 0 && instanceNum < shades.length)
            color = ContextCompat.getColor(context, shades[instanceNum]);
        return color;
    }
}
