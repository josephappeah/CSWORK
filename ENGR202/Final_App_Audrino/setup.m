%% 1. Specifies the COM port that the Arduino board is connected to
comPort = 'COM4';%This can be found out using the device manager (Windows)
                  %On a mac type ls /dev/tty* in Terminal and 
                  %  identify the device that is listed as usbmodem
                  %  Example for a MAC comPort = '/dev/tty.usbmodemfa131';
%comPort = '/dev/tty.usbmodemfd121';



%% 2. Initialize the Serial Port - setupSerial() (not to be altered)

%connect MATLAB to the accelerometer
if (~exist('serialFlag','var'))
 [accelerometer.s,serialFlag] = setupSerial(comPort);
end

%% 3. Run a calibration routine if needed - calibrate() (not to be altered)
 
%if the accelerometer is not calibrated, calibrate now
if(~exist('calCo', 'var'))
    calCo = calibrate(accelerometer.s);
end