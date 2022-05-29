import {Card, CardContent, Typography} from "@mui/material";
import React from "react";
import {observer} from "mobx-react";

const ResultInfoBasic = observer(({item}) => {
  return <Card variant={"outlined"}>
    <CardContent>
      <Typography variant={"subtitle1"} color={"text.primary"}>
        {item.title}
      </Typography>
      <Typography variant={"body1"} color={"text.secondary"}>
        {item.content}
      </Typography>
    </CardContent>
  </Card>;
})

export default ResultInfoBasic