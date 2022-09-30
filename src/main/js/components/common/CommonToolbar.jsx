import React from 'react';
import PropTypes from 'prop-types';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';

const RESUME_URL = 'https://s3.us-east-2.amazonaws.com/app.consultwithkyle.com/Kyle_Wegener_Resume_2022.pdf';
const GIT_URL = 'https://github.com/kwage/spring-boot-api';

const CommonToolbar = () => {

  const handleResumeButtonClick = () => {
    window.open(RESUME_URL, '_blank');
  }

  const handleGitButtonClick = () => {
    window.open(GIT_URL, '_blank');
  }

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static" style={{ backgroundColor: '#393e8f'}}>
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Disney API Interview
          </Typography>
          <Button color="inherit" onClick={handleGitButtonClick}>API Github</Button>
          <Button color="inherit" onClick={handleResumeButtonClick}>Kyle's Resume</Button>
        </Toolbar>
      </AppBar>
    </Box>
  )
}

CommonToolbar.propTypes = {
}

export default CommonToolbar;