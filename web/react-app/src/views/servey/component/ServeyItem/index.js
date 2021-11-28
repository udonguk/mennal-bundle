import {Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import PropTypes from 'prop-types';
import React, {useContext} from "react";
import {GlobalStoreContext} from "../../../../store/GlobalStoreProvider";
import {observer} from "mobx-react";

const SurveyItem = observer(({item}) => {
  const store = useContext(GlobalStoreContext);
  const surveyItemStore = store.surveyItemStore


  const yesClickHandler =  () => {
    item.setStatus('YES')
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
          color={"success"}
          fullWidth>
          YES
        </Button>
        <Button variant={'contained'} fullWidth>
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