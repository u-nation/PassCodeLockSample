# PassCodeLockSample
![](art/passcodelock.gif)

## Description
Hi, I'm [@u_nation](https://twitter.com/u_nation)  
I made a simple sample for implementing *passcode lock* like iOS.  
This sample owes the prime idea to manage application lifecycle to Mr.[@heki1224](https://twitter.com/heki1224) [Reference Link](http://www.slideshare.net/heki1224/android-45736528)
****
## Usage 
***Only API 15 over***  
You need implements Application.ActivityLifecycleCallbacks to your Application class.  
In order to manage Activity stack appropriately, please start your Activity after **activityStack.add(activity.hashCode());**.
```java
public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {
    private HashSet<Integer> activityStack = new HashSet<>();

    @Override
    public void onActivityStarted(Activity activity) {
        boolean isForeground = activityStack.size() == 0;
        activityStack.add(activity.hashCode());
        if (isForeground) activity.startActivity(/*Activity for entering passcode*/);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        activityStack.remove(activity.hashCode());
        boolean isBackground = activityStack.size() == 0;
    }
    ~~~~
}
```
  
***Activity for entering passcode***  
```java
    @Override
    protected void onPause() {
        super.onPause();
        finish(); //remove this Activity's stack
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true); //Application will be background without regard for Activity stack
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
```
## License

```
Copyright 2016 Takukya Endo

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
