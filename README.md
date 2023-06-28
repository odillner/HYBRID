## HYBRID

This is the hybrid benchmarking module of the thesis work. It performs the benchmarks on a given smartphone device. 

## Usage

This application is developed using node.js and NPM and as such requires these frameworks to be installed. It also uses Android studio and all resources necessary to run the native benchmarking application. It is recommended that you set up the web and native module beforehand, so that those resources are already ready.

This module makes heavy use of code from the native benchmarking module. If you wish to see documentation for that code, you'll find it there.  

Beyond that, getting React Native to run can be a bit of a mess. Everytime I need to set it up on a new computer, additional work is required.

Follow the steps in the official documentation: [https://reactnative.dev/docs/environment-setup](https://reactnative.dev/docs/environment-setup)

Once all necessary prerequisites have been installed, run "NPM install" in the root directory to install dependencies.

After that, open the Android folder in Android Studio and install all Gradle dependencies.

Once this is done, you are ready to deploy the application to a device by following this guide: [https://developer.android.com/studio/run/device](https://developer.android.com/studio/run/device)

Once the device is connected, use Android Studio to deploy the application.

When this is done, start the Metro server using "NPM start"

If the device fails to connect to the Metro server, run "adb reverse tcp:8081 tcp:8081" to configure ADB to correctly route the traffic.

At this point the application should be running correctly on the device, or maybe not, maybe you encounter some problem I haven't. 

The supplemental metrics such as CPU and memory utilizations need to be gathered using appropriate performance profilers
such as Firefox or Chrome Devtools utilities. 

In the project directory, you can run:

### `npm install`

Installs all dependencies. 

### `npm start`

Start the Metro server to which a device can connect and have to application deployed to.

# BEFORE STARTING BENCHMARKS, VISIT [https://data-gatherer.onrender.com/api/data](https://data-gatherer.onrender.com/api/data) OR YOUR DEPLOYED INSTANCE AND WAIT UNTIL IT LOADS. OTHERWISE THE INSTANCE MIGHT NOT BE RUNNING AND YOU RISK DISCARDING THE RESULTS