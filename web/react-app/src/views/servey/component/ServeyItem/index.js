import {Button, Card, CardActions, CardContent, Grid, Typography} from "@mui/material";
import PropTypes from 'prop-types';
import React from "react";
import {observer} from "mobx-react";

const SurveyItem = observer(({item, index}) => {

  return (
    <Card key={item.id}>
      <CardContent>
        <Typography>
          {`${index + 1}. ${item.title}  ${item.totalScore}`}
        </Typography>
      </CardContent>
      <CardActions>
        <Grid container rowSpacing={1} columnSpacing={{ xs: 1, sm: 2, md: 3}}>
          {item.surveyItemOptions.map(option =>
            <Grid item xs={6} key={option.id}>
              <Button
                key ={option.id}
                variant={'contained'}
                onClick={() => {
                  option.setUnCheckAll()
                  option.setIsChecked(true)
                }}
                color={option.isChecked ? 'success' : 'secondary'}
                fullWidth>
                {option.title}
              </Button>
            </Grid>
          )}
        </Grid>
      </CardActions>
    </Card>
  )
})

SurveyItem.propTypes = {
  item: PropTypes.object
}

export default SurveyItem;