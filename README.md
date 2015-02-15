# PassCodeLockSample

## Description
Androidでパスコードロックを実現するサンプルです

## Demo

![](/passcodelock.gif)

## Usage

Activityを作る時、ライフサイクルを監視する  
**LockObserverActivity**  
**LockObserverActionBarActivity**  
を継承してください。  

finish()メソッドで前画面に遷移する場合は  
finishToActivity()メソッドを呼び出してください。

AndroidAnnotationsを使用している場合
start()メソッドを呼ぶ前に、isTransitionにtrueを代入してください。

	LockObserverActivity.isTransition = true;
	SampleActivity_.intent(this).start();　　

  or

	LockObserverActionBarActivity.isTransition = true;  
	SampleActivity_.intent(this).start();  

その他でアプリが終了するわけではないが、onPause()が呼ばれる
場面では同じようにisTransitionにtrueを代入してください。

## NOTE

**パスコード入力画面に使う以下のxmlはそのまま使用せず**  
**各々デザインを変えて頂くようにお願いします。**  
activity_confirm_passcode.xml  
activity_input_pass_code.xml  

## Licence

特に設けてありません。上記のことだけ守って頂ければ改変、再配布など自由に行ってください。