import {Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import PropTypes from 'prop-types';
import React from "react";

const SurveyItem = ({item}) => {
  return (
    <Card key={`surveyItem-card-${item.code}`}>
      <CardContent>
        <Typography>
          {item.title}
        </Typography>
      </CardContent>
      <CardActions>
        <Button variant={'contained'} fullWidth>
          YES
        </Button>
        <Button variant={'contained'} fullWidth>
          NO
        </Button>
      </CardActions>
    </Card>
  )
}

SurveyItem.propTypes = {
  item: PropTypes.object
}

export default SurveyItem;