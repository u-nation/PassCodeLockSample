# PassCodeLockSample
====
## Description
Androidでパスコードロックを実現するサンプルです

## Demo

![](/passcodelock.gif)

## Usage

Activityを作る時、ライフサイクルを監視するLockObserverActivityかLockObserverActionBarActivityを
継承してください。  
  **パスコード入力画面に使う以下のxmlはそのまま使用せず各々デザインを変えて頂くようにお願いします。**  　
  activity_confirm_passcode.xml  
  activity_input_pass_code.xml  

AndroidAnnotationsを使用している場合
start()メソッドを呼ぶ前に、isTransitionにtrueを代入してください。

	LockObserverActivity.isTransition = true;
	SampleActivity_.intent(this).start();　　
  or　　
	LockObserverActionBarActivity.isTransition = true;  
	SampleActivity_.intent(this).start();  

## Licence

[MIT](https://github.com/tcnksm/tool/blob/master/LICENCE)