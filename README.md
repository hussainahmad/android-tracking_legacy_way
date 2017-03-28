#Google Tag Manager for Android App's



[Before we look at implementing GTM for android let's have a deeper look at official website][1]

##TAG
A tag is a piece of code that sends information to a third party, such as Google Analytics.

##TRIGGER
A trigger is a condition that evaluates to either true or false at runtime. Triggers attached to a tag
govern when the tag is fired or not fired.
All triggers are associated with a a event type.

##TRIGGER TYPE
Actually there are 9 built-in Firebase automatic event types and 2 other event types.

<p align="center">
    <img src="Screenshots/trigger_types.png" alt="trigger_types" width="40%"/>
</p>


##EVENT
An event can be a pageview, a click on a button, a form submmission or any custom event that you define.
Tag Manager has 4 built-in event types plus a custom event option.
Triggers from the container are evaluated and tags are fired accordingly. No tag can be fired unless an event occurs.
 


[1]: https://support.google.com/tagmanager#topic=

compose 
