function calCo= calibrate(s)
    out.s=s;
    calCo.offset=0;
    calCo.g=1;
    
    %read the raw accelerometer output at three different orientations
    mbox = msgbox( '               Level Out.', 'Calibration'); uiwait(mbox);
    [gx_z gy_z gz_z] = readAcc(out,calCo);   %gZ = 1, gX = gY = 0 orientation

    mbox = msgbox('               Bank Right.', 'Calibration'); uiwait(mbox); 
    [gx_x gy_x gz_x] = readAcc(out,calCo);   %gX = 1, gY = gZ = 0 orientation

    mbox = msgbox('               Pull Up.', 'Calibration'); uiwait(mbox);
    [gx_y gy_y gz_y] = readAcc(out,calCo);   %gY = 1, gX = gZ = 0 orientation

    %calculate offsets for each axis
    offsetX = (gx_z + gx_y) / 2;
    offsetY = (gy_x + gy_z) / 2;
    offsetZ = (gz_x + gz_y) / 2;

    %calculate scaling factors
    gainX = gx_x - offsetX;
    gainY = gy_y - offsetY;
    gainZ = gz_z - offsetZ;

    calCo.offset = [offsetX offsetY offsetZ];
    calCo.g = [gainX gainY gainZ];

 end