import {Avatar, Card, CardContent, CardHeader, Typography} from "@mui/material";
import React from "react";
import {observer} from "mobx-react";

const ResultInfoBasic = observer(({index, item}) => {
  return <Card variant={"outlined"}>
    <CardHeader title={item.title}
                avatar={<Avatar variant={'rounded'}
                                sx={{ width: 24, height: 24 }}>{index + 1}</Avatar>}/>
    <CardContent>
      <Typography variant={"body1"} color={"text.secondary"}>
        {item.content}
      </Typography>
    </CardContent>
  </Card>;
})

export default ResultInfoBasic
