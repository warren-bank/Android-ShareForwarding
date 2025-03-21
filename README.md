### [ShareForwarding](https://github.com/warren-bank/Android-ShareForwarding)

Android app to extract and forward data from shared text.

Forwarding allows the extracted data to be opened in a different app that is specifically meant for the particular type of data.
When more than one such app is installed, the user can choose from a list.

#### Supported Data Types

* [email address](https://developer.android.com/reference/android/util/Patterns#EMAIL_ADDRESS)
  - example: [`me@example.com`](https://developer.android.com/guide/components/intents-common.html#Email)
* [web URL](https://developer.android.com/reference/android/util/Patterns#WEB_URL)
  - example: [`http://www.example.com/path/to/page.html`](https://developer.android.com/guide/components/intents-common.html#Browser)
* [map GPS coordinates](https://stackoverflow.com/a/18690202)
  - example: [`38.897957,-77.036560`](https://developer.android.com/guide/components/intents-common.html#Maps)
* [phone number](https://developer.android.com/reference/android/util/Patterns#PHONE)
  - example: [`+1-800-555-1212`](https://developer.android.com/guide/components/intents-common.html#Phone)

#### Legal

* copyright: [Warren Bank](https://github.com/warren-bank)
* license: [GPL-2.0](https://www.gnu.org/licenses/old-licenses/gpl-2.0.txt)
