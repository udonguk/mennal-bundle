import {makeAutoObservable, observable, reaction} from "mobx";
import _ from "lodash";
import {SurveyItemOptionDomain} from "./SurveyItemOptionDomain";

export class SurveyItemDomain {
  id = ""
  title = ""
  orderNum = ""
  useYn = ""
  regDt = null
  editDt = null
  delDt = null
  surveyItemOptions = []

  isChecked = false
  status = ""

  store = null
  saveHandler = null

  constructor(store, item) {
    makeAutoObservable(this, {
      id: observable,
      surveyItemOptions: observable,
      isChecked: observable,

    })
    this.store = store

    this.id = item.id
    this.title = item.title
    this.orderNum = item.orderNum
    this.useYn = item.useYn
    this.regDt = item.regDt
    this.editDt = item.editDt
    this.delDt = item.delDt


    this.surveyItemOptions = []
    if(!_.isNil(item.surveyItemOptionEntities)){
      item.surveyItemOptionEntities.forEach(
        surveyItemOption => this.surveyItemOptions.push(new SurveyItemOptionDomain(this, surveyItemOption)))
    }

    this.saveHandler = reaction(
      () => this.asJson,
      json => {
        // doSomeThing
      }
    )
  }

  setStatus(param){
    this.isChecked = true
    this.status = param
  }


  delete() {

  }

  get asJson() {

  }

  dispose() {

  }
}