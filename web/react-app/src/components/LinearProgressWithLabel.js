import React from "react";
import {Box, LinearProgress, Typography} from "@mui/material";

const  LinearProgressWithLabel = (props) => {
  return (
    <Box sx={{ display: 'flex', alignItems: 'center' }}>
      <Box sx={{ width: '100%', mr: 1 }}>
        <LinearProgress variant="determinate" color='secondary' {...props} sx={{
          height: 10,
        }}/>
      </Box>
      <Box sx={{ minWidth: 45 }}>
        <Typography variant="body" color="text.secondary">
          {props.info}
        </Typography>
      </Box>
    </Box>
  );
}

export default LinearProgressWithLabel
