# Google Tag Manager for Android App's


[Before we look at implementing GTM for android let's have a deeper look at official website][1]

## TAG
A tag is a piece of code that sends information to a third party, such as Google Analytics.

## TRIGGER
A trigger is a condition that evaluates to either true or false at runtime. Triggers attached to a tag
govern when the tag is fired or not fired.
All triggers are associated with a a event type.

## TRIGGER TYPE
Actually there are 9 built-in Firebase automatic event types and 2 other event types.

<p align="center">
    <img src="Screenshots/trigger_types.png" alt="trigger_types" width="40%"/>
</p>


## EVENT
An event can be a pageview, a click on a button, a form submmission or any custom event that you define.
Tag Manager has 4 built-in event types plus a custom event option.
Triggers from the container are evaluated and tags are fired accordingly. No tag can be fired unless an event occurs.
 


# Implementing Universal Analytics and Google Tag Manager SDK for android

Before beginnig   


# Getting Started

1. Go to [tagmanager-google.com][2] to create a Tag Manager account(or to access an existing account).
2. When you logged-in to Google Tag Manager you'll see option's tab as follow below:

<p align="center">
    <img src="Screenshots/option_tabs_tagmanager.png" alt="trigger_types" width="30%"/>
</p>



3. Click new account and set up our container:

<p align="center">
    <img src="Screenshots/new_account.png" alt="trigger_types" width="40%"/>
</p>


### Firebase(Android)
If we select this Android SDK version, we always download a json file for our project's implementation. 

### Legacy Android
If we select this Android SDK version, we can download a binary file necessary for the  
'loadContainerPreferNonDefault' method of the Google Play Service SDK.
See the code's snippet below:

```shell
PendingResult<ContainerHolder> pending =
                tagManager.loadContainerPreferNonDefault(CONTAINER_ID,
                        R.raw.orbismobiletracking_binarie);
```

For our case we'll be using the Legacy Android SDK version




[For more information about category,action, label , value][4]





[1]: https://support.google.com/tagmanager#topic=
[2]: https://tagmanager.google.com/#/home
[3]: https://developers.google.com/tag-manager/android/v4/ua#create-screen-name-var
[4]: https://support.google.com/analytics/answer/6164470?hl=en

