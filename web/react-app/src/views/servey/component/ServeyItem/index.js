import {Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import PropTypes from 'prop-types';
import React from "react";
import {observer} from "mobx-react";

const SurveyItem = observer(({item}) => {

  const yesClickHandler = () => {
    item.setStatus('YES')
  }

  const noClickHandler = () => {
    item.setStatus('NO')
  }

  return (
    <Card>
      <CardContent>
        <Typography>
          {item.title}
        </Typography>
      </CardContent>
      <CardActions>
        <Button
          variant={'contained'}
          onClick={yesClickHandler}
          color={'YES' === item.status ? 'success' : 'secondary'}
          fullWidth>
          YES
        </Button>
        <Button variant={'contained'}
                onClick={noClickHandler}
                color={'NO' === item.status ? 'success' : 'secondary'}
                fullWidth>
          NO
        </Button>
      </CardActions>
    </Card>
  )
})

SurveyItem.propTypes = {
  item: PropTypes.object
}

export default SurveyItem;