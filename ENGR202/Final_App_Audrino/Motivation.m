%% Motivation
% in your opening function, set the these 2 lines in the gui

[handles.Y, handles.FS] = wavread('Motivation.mp3');  % handles. is for the gui
% EPED.wav is the name of the wav file in the same folder as the Gui
    handles.player = audioplayer(handles.Y, handles.FS); % Sets the music 
                                                     % to an audio player
    
% Be careful with the size of the file.  The larger the file the longer the
% waveread will take
    
    %% Playing music in the Gui
    % I used a music button.  therefore the music is not in the While loop
    
    % in the While loop or anywhere else use the Commands
    play(handles.player) % this will play the song you have set to the 
                                    % audio player
    
                                    
   % To stop the song use
   stop(player) % Stops the song
   
   
   %% Example Code for your Gui
   
   % Set an if statement for if it moves right
   if gx goes right
       play(player)
   elseif gx goes left
       play(player)
       