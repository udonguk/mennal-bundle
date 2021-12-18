import {Box, Typography} from "@mui/material";
import MyResponsiveRadar from "../../components/graph/MyResponsiveRadar";
import React, {useContext} from "react";
import {observer} from "mobx-react";
import {StoresContext} from "../../store/RootStore";

const tempGraphData = [
  {
    "faction": "학습의지",
    "chardonay": 31,
  },
  {
    "faction": "학습행동",
    "chardonay": 40,
  },
  {
    "faction": "학습신념",
    "chardonay": 34,
  },
];


const Result = observer(() => {
    const store = useContext(StoresContext);
    const surveyItemStore = store.surveyItemStore

    return (
      <>
        <Typography>
          {surveyItemStore.result.categoryType}
        </Typography>
        <Box maxWidth height={500}>
          <MyResponsiveRadar data={surveyItemStore.graphFormat} />
        </Box>
        {surveyItemStore.result.factionList.map(item => {
          return (<>
            <Typography variant={"body"} color={"text.primary"}>
              {item.faction}
            </Typography>
              <Typography variant={"body2"} color={"text.secondary"}>
                {item.title}
              </Typography>
          </>
          )
        })}
      </>
    )
  }
)
export default Result;