 function varargout = Space_Invaders(varargin)
 bots = []; %list of current bots
 
% SPACE_INVADERS MATLAB code for Space_Invaders.fig
%      SPACE_INVADERS, by itself, creates a new SPACE_INVADERS or raises the existing
%      singleton*.
%
%      H = SPACE_INVADERS returns the handle to a new SPACE_INVADERS or the handle to
%      the existing singleton*.
%
%      SPACE_INVADERS('CALLBACK',hObject,eventData,handles,...) calls the local
%      function named CALLBACK in SPACE_INVADERS.M with the given input arguments.
%
%      SPACE_INVADERS('Property','Value',...) creates a new SPACE_INVADERS or raises the
%      existing singleton*.  Starting from the left, property value pairs are
%      applied to the GUI before Space_Invaders_OpeningFcn gets called.  An
%      unrecognized property name or invalid value makes property application
%      stop.  All inputs are passed to Space_Invaders_OpeningFcn via varargin.
%
%      *See GUI Options on GUIDE's Tools menu.  Choose "GUI allows only one
%      instance to run (singleton)".
%
% See also: GUIDE, GUIDATA, GUIHANDLES

% Edit the above text to modify the response to help Space_Invaders

% Last Modified by GUIDE v2.5 06-Mar-2014 14:57:52

% Begin initialization code - DO NOT EDIT
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @Space_Invaders_OpeningFcn, ...
                   'gui_OutputFcn',  @Space_Invaders_OutputFcn, ...
                   'gui_LayoutFcn',  [] , ...
                   'gui_Callback',   []);
if nargin && ischar(varargin{1})
    gui_State.gui_Callback = str2func(varargin{1});
end

if nargout
    [varargout{1:nargout}] = gui_mainfcn(gui_State, varargin{:});
else
    gui_mainfcn(gui_State, varargin{:});
end
% End initialization code - DO NOT EDIT

% --- Executes just before Space_Invaders is made visible.
function Space_Invaders_OpeningFcn(hObject, eventdata, handles, varargin)
% This function has no output args, see OutputFcn.
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
% varargin   command line arguments to Space_Invaders (see VARARGIN)

% Choose default command line output for Space_Invaders
handles.output = hObject;

% Update handles structure
guidata(hObject, handles);

% --- Outputs from this function are returned to the command line.
function varargout = Space_Invaders_OutputFcn(hObject, eventdata, handles) 
% varargout  cell array for returning output args (see VARARGOUT);
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Get default command line output from handles structure
varargout{1} = handles.output;

% --- Executes on button press in Serial.
function Serial_Callback(hObject, eventdata, handles)
% hObject    handle to Serial (see GCBO)
% eventdata  reserved - to be defiCOM4ned in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
handles.comPort = 'COM1';
if (~exist('handles.serialFlag','var'))
    [handles.accelerometer.s,handles.serialFlag]= setupSerial(handles.comPort);
end
set(handles.Serial,'String','CLOSED');
guidata(hObject, handles);

% --- Executes on button press in calibrate.
function calibrate_Callback(hObject, eventdata, handles)
% hObject    handle to calibrate (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
%Calibrating the accelerometer vectors
if(~exist('handles.calCo', 'var'))
    handles.calCo = calibrate(handles.accelerometer.s);
end
% Get the new values from the accelerometer
[handles.gx handles.gy handles.gz] = readAcc(handles.accelerometer, handles.calCo);
set(handles.calibrate,'String','SET');
guidata(hObject, handles);

% --- Executes on selection change in difficulty.
function difficulty_Callback(hObject, eventdata, handles)
% hObject    handle to difficulty (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% --- Executes during object creation, after setting all properties.
function difficulty_CreateFcn(hObject, eventdata, handles)
% hObject    handle to difficulty (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: popupmenu controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end

% --- Executes on mouse press over axes background.
function axes1_ButtonDownFcn(hObject, eventdata, handles)
% hObject    handle to axes1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
eventdata;
global gun
if strcmp( get(handles.figure1,'selectionType') , 'normal')
gun = 1;
end
% --- Executes on button press in start.
function start_Callback(hObject, eventdata, handles)
% hObject    handle to start (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
global gun
handles.g=get(handles.start,'String');
if (strcmp(handles.g,'LAUNCH'));
set(handles.start,'String','SELF DESTRUCT');
    % Turn off all buttons when the game is in running
    set(handles.Serial,'Visible','off');
    set(handles.calibrate,'Visible','off');
    set(handles.difficulty,'Enable','off');
    set(handles.sense,'Visible','off');
    set(handles.music,'Visible','off');
    set(handles.text2,'Visible','off');
    set(handles.close,'Visible','off');
guidata(hObject, handles);
else
set(handles.start,'String','LAUNCH');
    set(handles.Serial,'Visible','on');
    set(handles.calibrate,'Visible','on');
    set(handles.difficulty,'Enable','on');
    set(handles.sense,'Visible','on');
    set(handles.music,'Visible','on');
    set(handles.text2,'Visible','on');
    set(handles.close,'Visible','on');
guidata(hObject, handles);
end




% Variables
handles.DifficultyType = get(handles.difficulty, {'String', 'Value'});
handles.diff = handles.DifficultyType{1} {handles.DifficultyType{2}};
% setup all the data storage to be filled later
buf_len = 50;
index = 1:buf_len;
% for the low pass filter
handles.gxFilt = 0;
handles.gyFilt = 0;
handles.gzFilt = 0;
handles.resFilt = 0;
handles.gxFiltdata = zeros(buf_len,1);
handles.gyFiltdata = zeros(buf_len,1);
handles.gzFiltdata = zeros(buf_len,1);
handles.resFiltdata = zeros(buf_len,1);
% Laser Shooting

% Key Values
handles.life=3; % lives
handles.border = 8; % set the size of the game map
handles.movespeed = .25; % Speed of the Ship
handles.Sensitivity = get(handles.sense,'Value');  % To control the senitivity of the Accelerometer
difficulty = 0; % set the Diffuculty to zero

% Draw the ship

 %----- Ship Design ------%
% all x componets of the Patch Function
    handles.x = [0 .07 .1 .1 .15 .16 .39 .4 .2 .2 .22 .23 .65 .65 .27 .3 .3 .225 .225 .2 .15 .12 .03 .01 0 ...
        -0.01 -0.03 -0.12 -0.15 -0.2 -0.225 -0.225 -0.3 -0.3 -0.27 -0.65 -0.65 -0.23 -0.22 -0.2 -0.2 -0.4 -0.39 -0.16 -0.15 -0.1 -0.1 -0.07 0];
% all y componets of the Patch Function
    handles.y=[2 1.8 1.5 1.45 1.47 1.4 1.18 1.1 1.14 1.1 1 .9 .48 .32 .25 .2 .05 .12 .08 .07 .2 .13 .13 .2 .18 ...
            .2 .13 .13 .2 .07 .08 .12 .05 .2 .25 .32 .48 .9 1 1.1 1.14 1.1 1.18 1.4 1.47 1.45 1.5 1.8 2] - [10];
    L = length(handles.x);
    handles.jet = [handles.x; handles.y; ones(1, L)];
% Draw the decal
    handles.x1=[.01 .03 .03 .07 .1 .1 .115 .15 .2 .15 .1 .03 .01 0 -.01 -.03 -.1 -.15 -.2 -.15 -.115 -.1 -.1 -.07 -.03 -.03 -.01];
    handles.y1=[1.4 1.35 1.25 1.15 1.03 .9 .75 .45 .4 .2 .26 .26 .2 .18 .2 .26 .26 .2 .4 .45 .75 .9 1.03 1.15 1.25 1.35 1.4] - [10];
    L1=length(handles.x1);    
    handles.jet1 = [handles.x1; handles.y1; ones(1, L1)];
% Explosion
    handles.x2=[0 .1 .2 .2 0.5 .3 .5 .4 .7 .5 0.6 .4 .7 .6 .8 .7 0.8 .6 .6 0.4 .3 0.2 0.1 0 -0.1 -0.2 -0.3 -0.4 -0.6 -0.6 -0.8 -0.7 -0.8 -0.6 -0.7 -0.4 -0.6 -0.5 -0.7 -0.4 -0.5 -0.3 -0.5 -0.2 -0.2 -0.1 0];
    handles.y2=[2.3 2 2.1 1.9 2 1.6 1.5 1.4 1.3 1.1 1 .9 .8 .7 .5 .4 .1 .2 -0.1 .1 -0.1 0 -0.1 .1 -0.1 0 -0.1 .1 -0.1 .2 .1 .4 .5 0.7 .8 .9 1 1.1 1.3 1.4 1.5 1.6 2 1.9 2.1 2 2.3] - [10];    
    L2=length(handles.x2);
    handles.jetE = [handles.x2*2.5; handles.y2; ones(1, L2)];
    handles.jetE1 = [handles.x2*1.7; handles.y2; ones(1, L2)];
    handles.jetE2 = [handles.x2*1.1; handles.y2; ones(1, L2)];
% Draw the enemy ships
    handles.x4=[0 .1 .15 .25 .2 .3 .2 .1 -.1 -.2 -.3 -.2 -.25 -.15 -.1 0];
    handles.y4=[.5 .5 .6 .6 .5 .3 0 .2 .2 0 .3 .5 .6 .6 .5 .5];
    L4=length(handles.x4);
    handles.alien = [handles.x4; handles.y4; ones(1, L4)];

% Run the Game
if (strcmp(handles.diff,'LEVEL'))
    mbox = msgbox( 'Fatal Error, You Must First Pick A Level.', 'FAILURE IS ALWAYS AN OPTION'); 
    uiwait(mbox);
    set(handles.start,'String','LAUNCH');
    set(handles.Serial,'Visible','on');
    set(handles.calibrate,'Visible','on');
    set(handles.difficulty,'Enable','on');
    set(handles.sense,'Visible','on');
    set(handles.music,'Visible','on');
    set(handles.text2,'Visible','on');
    set(handles.close,'Visible','on');
    guidata(hObject, handles);
else
while (strcmp(get(handles.start,'String'),'SELF DESTRUCT'))
    
%read accelerometer output
    [handles.gx handles.gy handles.gz] = readAcc(handles.accelerometer, handles.calCo);
    guidata(hObject, handles);
% setup of the low pass filter
    handles.alpha=(.5);
    handles.gxFilt = ((1-handles.alpha)*handles.gxFilt) + (handles.alpha*handles.gx);
    handles.gyFilt = ((1-handles.alpha)*handles.gyFilt) + (handles.alpha*handles.gy);
    handles.gzFilt = ((1-handles.alpha)*handles.gzFilt) + (handles.alpha*handles.gz);
    handles.resFilt = sqrt(handles.gxFilt^2+handles.gyFilt^2 + handles.gzFilt^2);
    guidata(hObject, handles);
% Low Pass Matrices
    handles.gxFiltdata = [handles.gxFiltdata(2:end) ; handles.gxFilt];
    handles.gyFiltdata = [handles.gyFiltdata(2:end) ; handles.gyFilt];
    handles.gzFiltdata = [handles.gzFiltdata(2:end) ; handles.gzFilt];
    handles.resFiltdata= [handles.resFiltdata(2:end) ; sqrt(handles.gxFilt^2+handles.gyFilt^2+handles.gzFilt^2)];
    guidata(hObject, handles);
% Plotting the fighter   
    axes(handles.axes1)
    cla;            %clear everything from the current axis
% Set the border and color the graph black
    axis([-handles.border handles.border -handles.border handles.border])
    colordef black;
    set(gca, 'XTickLabel','','YTickLabel','','XColor','k','YColor','k');

% Set up the boundaries the ship is allowed to move in
% X border
   if handles.jet(1,14) >= handles.border
       handles.jet(1,:) = handles.jet(1,:) - handles.movespeed;
       handles.jet1(1,:) = handles.jet1(1,:) - handles.movespeed;
       handles.jetE(1,:) = handles.jetE(1,:) - handles.movespeed;
       handles.jetE1(1,:) = handles.jetE1(1,:) - handles.movespeed;
       handles.jetE2(1,:) = handles.jetE2(1,:) - handles.movespeed;
       guidata(hObject, handles);
   
   elseif handles.jet(1,36) <= -handles.border
       handles.jet(1,:) = handles.jet(1,:) + handles.movespeed;
       handles.jet1(1,:) = handles.jet1(1,:) + handles.movespeed;
       handles.jetE(1,:) = handles.jetE(1,:) + handles.movespeed;
       handles.jetE1(1,:) = handles.jetE1(1,:) + handles.movespeed;
       handles.jetE2(1,:) = handles.jetE2(1,:) + handles.movespeed;
       guidata(hObject, handles);
       
   end
% Y border
   if handles.jet(2,1) >= (handles.border-(handles.border*1.333))
       handles.jet(2,:) = handles.jet(2,:) - handles.movespeed;
       handles.jet1(2,:) = handles.jet1(2,:) - handles.movespeed;
       handles.jetE(2,:) = handles.jetE(2,:) - handles.movespeed;
       handles.jetE1(2,:) = handles.jetE1(2,:) - handles.movespeed;
       handles.jetE2(2,:) = handles.jetE2(2,:) - handles.movespeed;
       guidata(hObject, handles);
   
   elseif handles.jet(2,17) <= -(handles.border-.3)
       handles.jet(2,:) = handles.jet(2,:) + handles.movespeed;
       handles.jet1(2,:) = handles.jet1(2,:) + handles.movespeed;
       handles.jetE(2,:) = handles.jetE(2,:) + handles.movespeed;
       handles.jetE1(2,:) = handles.jetE1(2,:) + handles.movespeed;
       handles.jetE2(2,:) = handles.jetE2(2,:) + handles.movespeed;
       guidata(hObject, handles);
   end    
    
    
% Control the movement of the ship    
% Move Right    
if handles.gxFilt > handles.Sensitivity
       handles.jet(1,:) = handles.jet(1,:) + handles.movespeed;
       handles.jet1(1,:) = handles.jet1(1,:) + handles.movespeed;
       handles.jetE(1,:) = handles.jetE(1,:) + handles.movespeed;
       handles.jetE1(1,:) = handles.jetE1(1,:) + handles.movespeed;
       handles.jetE2(1,:) = handles.jetE2(1,:) + handles.movespeed;   
     guidata(hObject, handles);
     
% Move Left   
elseif handles.gxFilt < -handles.Sensitivity     
       handles.jet(1,:) = handles.jet(1,:) - handles.movespeed; 
       handles.jet1(1,:) = handles.jet1(1,:) - handles.movespeed;
       handles.jetE(1,:) = handles.jetE(1,:) - handles.movespeed;
       handles.jetE1(1,:) = handles.jetE1(1,:) - handles.movespeed;
       handles.jetE2(1,:) = handles.jetE2(1,:) - handles.movespeed;
     guidata(hObject, handles);
       
% Stay still when not tilted   
elseif handles.gxFilt > -handles.Sensitivity  && handles.gxFilt < handles.Sensitivity 
       handles.jet(1,:) = handles.jet(1,:);
       handles.jet1(1,:) = handles.jet1(1,:);
       handles.jetE(1,:) = handles.jetE(1,:);
       handles.jetE1(1,:) = handles.jetE1(1,:);
       handles.jetE2(1,:) = handles.jetE2(1,:);
       guidata(hObject, handles);
end
% Move up
if handles.gyFilt > handles.Sensitivity  
       handles.jet(2,:) = handles.jet(2,:) - handles.movespeed;
       handles.jet1(2,:) = handles.jet1(2,:) - handles.movespeed;
       handles.jetE(2,:) = handles.jetE(2,:) - handles.movespeed;
       handles.jetE1(2,:) = handles.jetE1(2,:) - handles.movespeed;
       handles.jetE2(2,:) = handles.jetE2(2,:) - handles.movespeed;
       guidata(hObject, handles);
       
% Move down
elseif handles.gyFilt < -handles.Sensitivity 
       handles.jet(2,:) = handles.jet(2,:) + handles.movespeed;
       handles.jet1(2,:) = handles.jet1(2,:) + handles.movespeed;
       handles.jetE(2,:) = handles.jetE(2,:) + handles.movespeed;
       handles.jetE1(2,:) = handles.jetE1(2,:) + handles.movespeed;
       handles.jetE2(2,:) = handles.jetE2(2,:) + handles.movespeed;
       guidata(hObject, handles);
     
% Stay still when not tilted
elseif handles.gyFilt > -handles.Sensitivity  && handles.gyFilt < handles.Sensitivity   
       handles.jet(2,:) = handles.jet(2,:);
       handles.jet1(2,:) = handles.jet1(2,:);
       handles.jetE(2,:) = handles.jetE(2,:);
       handles.jetE1(2,:) = handles.jetE1(2,:);
       handles.jetE2(2,:) = handles.jetE2(2,:);
       guidata(hObject, handles);
end
% Draw the ship
     patch( handles.jet(1,:), handles.jet(2,:), 'y');
     patch( handles.jet1(1,:), handles.jet1(2,:), 'b');

% Fire the laser
% The Laser
    handles.laser=[handles.x(1) handles.x(8) handles.x(8) handles.x(1) handles.x(42) handles.x(42); ...
        handles.y(1)+.3 handles.y(1)+.5 handles.y(1)+1 handles.y(1)+1.2 handles.y(1)+1 handles.y(1)+.5; ones(1, 6)];
    
if gun == 1;
    for i=handles.laser(2,1):.1:handles.border
        i
        handles.laser(2,:)= handles.laser(2,:) + .1;
        patch(handles.laser(1,:), handles.laser(2,:), 'g');
    end
        
    gun=0;
    else 
    handles.laser(2,:) = [handles.y(1)+.3 handles.y(1)+.5 handles.y(1)+1 handles.y(1)+1.2 handles.y(1)+1 handles.y(1)+.5];        
end 


handles.life = handles.life - 1;
set(handles.lives,'String',handles.life);


guidata(hObject, handles);
 end
end
guidata(hObject, handles);

% Explosion if self destruct is hit
if (strcmp(get(handles.start,'String'),'LAUNCH'))

patch(handles.jetE(1,:), handles.jetE(2,:), 'r');
patch(handles.jetE1(1,:), handles.jetE1(2,:), 'y');
patch(handles.jetE2(1,:), handles.jetE2(2,:), 'r');
end

% --- Executes on button press in close.
function close_Callback(hObject, eventdata, handles)
% hObject    handle to close (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
set(handles.close,'String','BOOM');
run closeSerial.m

% --- Executes on button press in music.
function music_Callback(hObject, eventdata, handles)
% hObject    handle to music (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Plays motivational Music
[handles.y, handles.fs] = audioread('Motivation.wav');
handles.player = audioplayer(handles.y, handles.fs);

handles.mus=get(handles.music,'String'); 
if (strcmp(handles.mus,'Motivation'));
set(handles.music,'String','Motivated');
play(handles.player);
guidata(hObject, handles);
end

if (strcmp(handles.mus,'Motivated'));
set(handles.music,'String','Get Some'); 
stop(handles.player);
guidata(hObject, handles);
end

if (strcmp(handles.mus,'Get Some'));
set(handles.music,'String','Hurrah');
play(handles.player);
guidata(hObject, handles);
end

if (strcmp(handles.mus,'Hurrah'));
set(handles.music,'String','Why No Sound?');
stop(handles.player);
guidata(hObject, handles);
end

if (strcmp(handles.mus,'Why No Sound?'));
set(handles.music,'String','Motivation');

guidata(hObject, handles);
end

% --- Executes on slider movement.
function sense_Callback(hObject, eventdata, handles)
% hObject    handle to sense (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% --- Executes during object creation, after setting all properties.
function sense_CreateFcn(hObject, eventdata, handles)
% hObject    handle to sense (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: slider controls usually have a light gray background.
if isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor',[.9 .9 .9]);
end


