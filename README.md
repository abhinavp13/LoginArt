Android LoginArt App
====================
Android Open Source app providing an interesting login/register interface using scene transitions introduced in api level 19 (Kitkat).
Also, showcases animated logo working with SVG paths. 

Sample Video
============
[Youtube Sample Video](https://www.youtube.com/watch?v=3KVk6ujXoVc)

Screenshots
===========
![Demo Screenshot 2][1] ![Demo Screenshot 4][2] ![Demo Screenshot 3][3] 

Attributions & Inspirations
---------------------------
* The class `SvgPathParser` and `AnimatedLogoView` used to convert from string SVG Path format to Android SDK `Path` structures and further used to render on screen, has been obtained from the interesting [Roman Nurik](https://github.com/romannurik) [SVGPathParser](https://github.com/romannurik/muzei/blob/master/main%2Fsrc%2Fmain%2Fjava%2Fcom%2Fgoogle%2Fandroid%2Fapps%2Fmuzei%2Futil%2FSvgPathParser.java)  and [AnimatedMuzeiLogoView] (https://github.com/romannurik/muzei/blob/master/main/src/main/java/com/google/android/apps/muzei/util/AnimatedMuzeiLogoView.java) code.
* Animations are inspired by :
  ** [Material up - Login Mockup post](http://www.materialup.com/posts/login-mockup) created by [neel](http://www.materialup.com/neel2292).
  ** [Github - AndroidFillableLoaders](https://github.com/JorgeCastilloPrz/AndroidFillableLoaders) created by [JorgeCastilloPrz](http://jorgecastillo.xyz/).
  ** [Github - Muzei Live Wallpaper](https://github.com/romannurik/muzei) created by [Roman Nurik](http://roman.nurik.net/).

Developed By
------------
* Abhinav Puri - <pabhinav@iitrpr.ac.in>

License
-------

    Copyright 2016 Abhinav Puri

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: ./demogif/svgPathAnimation.gif
[2]: ./demogif/ballBouncingAndRevealEffect.gif
[3]: ./demogif/loginAndRegister.gif
