import React from 'react';
import PropTypes from 'prop-types';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';

const RESUME_URL = 'https://s3.us-east-2.amazonaws.com/app.consultwithkyle.com/Kyle_Wegener_Resume_2022.pdf';

const CommonToolbar = () => {

  const handleResumeButtonClick = () => {
    window.open(RESUME_URL, '_blank');
  }

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Disney API Interview
          </Typography>
          <Button color="inherit" onClick={handleResumeButtonClick}>API Github</Button>
          <Button color="inherit" onClick={handleResumeButtonClick}>Kyle's Resume</Button>
        </Toolbar>
      </AppBar>
    </Box>
  )
}

CommonToolbar.propTypes = {
}

export default CommonToolbar;