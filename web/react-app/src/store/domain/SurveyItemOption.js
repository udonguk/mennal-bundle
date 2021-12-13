import {makeAutoObservable, observable, reaction} from "mobx";

export class SurveyItemOption {
  id = ""
  title = ""
  orderNum = 0
  useYn = 'Y'
  regDt = null
  editDt = null
  delDt = null

  result = ""

  isChecked = false
  status = ""

  store = null
  saveHandler = null

  constructor(store, item) {
    makeAutoObservable(this, {
      id:observable,
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

    this.saveHandler = reaction(
      () => this.asJson,
      json => {
        // doSomeThing
      }
    )
  }
}