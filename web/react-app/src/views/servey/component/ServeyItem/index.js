import {Button, Card, CardActions, CardContent, Grid, Typography} from "@mui/material";
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
    <Card key={item.id}>
      <CardContent>
        <Typography>
          {`${index + 1}. ${item.title}`}
        </Typography>
      </CardContent>
      <CardActions>
        <Grid container rowSpacing={1} columnSpacing={{ xs: 1, sm: 2, md: 3}}>
          {item.surveyItemOptions.map(option =>
            <Grid item xs={6} key={option.id}>
              <Button
                key ={option.id}
                variant={'contained'}
                // onClick={yesClickHandler}
                color={'Y' === option.checked ? 'success' : 'secondary'}
                fullWidth>
                {option.title}
              </Button>
            </Grid>
          )}
        </Grid>
        {/*<Button
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
        </Button>*/}
      </CardActions>
    </Card>
  )
})

SurveyItem.propTypes = {
  item: PropTypes.object
}

export default SurveyItem;