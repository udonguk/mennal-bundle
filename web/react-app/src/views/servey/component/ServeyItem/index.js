import {Avatar, Button, Card, CardActions, CardContent, CardHeader, Divider, Grid, Typography} from "@mui/material";
import PropTypes from 'prop-types';
import React, {useState} from "react";
import {observer} from "mobx-react";

const SurveyItem = observer(({item, index}) => {
  const [isChecked, setIsChecked] = useState(false)

  return (
    <Card key={item.id} variant="outlined">
      <CardHeader avatar={<Avatar variant={'rounded'}
                                  sx={{ width: 24, height: 24 }}>{index + 1}</Avatar>}
                  title={item.title}/>
      <Divider />
      <CardActions>
        <Grid container rowSpacing={1} columnSpacing={{ xs: 1, sm: 2, md: 3}}>
          {item.surveyItemOptions.map(option =>
            <Grid item xs={6} key={option.id}>
              <Button
                id={`[id]${option.id}[order]${option.orderNum}`}
                key ={option.id}
                variant={option.isChecked ? 'contained' : 'outlined'}
                onClick={() => {
                  option.setUnCheckAll()
                  option.setIsChecked(true)
                  if(!isChecked){
                    setIsChecked(true)
                  }
                }}
                color={option.isChecked ? 'secondary' : 'primary'}
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
