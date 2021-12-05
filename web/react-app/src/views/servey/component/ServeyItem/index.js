import {Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import PropTypes from 'prop-types';
import React from "react";
import {observer} from "mobx-react";

const SurveyItem = observer(({item, index}) => {

  const yesClickHandler = () => {
    item.setStatus('Y')
  }

  const noClickHandler = () => {
    item.setStatus('N')
  }

  return (
    <Card>
      <CardContent>
        <Typography>
          {`${index + 1}. ${item.title}`}
        </Typography>
      </CardContent>
      <CardActions>
        <Button
          variant={'contained'}
          onClick={yesClickHandler}
          color={'Y' === item.status ? 'success' : 'secondary'}
          fullWidth>
          YES
        </Button>
        <Button variant={'contained'}
                onClick={noClickHandler}
                color={'N' === item.status ? 'success' : 'secondary'}
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