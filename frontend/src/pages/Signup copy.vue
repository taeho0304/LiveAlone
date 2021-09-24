<template>
    <div>
        <div class="container">
            <alert v-show="this.isBusiness" type="danger">기업회원일 경우 사업자 번호가 필요합니다!</alert>
            <tabs style="margin-top: 0px;" type="primary" tabContentClasses="tab-subcategories" centered="centered">
                <tab-pane>
                    <span slot="label" @click="click()">
                        <i class="now-ui-icons business_badge"></i>
                        일반회원
                    </span>
                    <div class="row ml-auto mr-auto">
                        <fg-input class="input col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="checkName()" placeholder="Name" v-model="user.userName" addon-left-icon="now-ui-icons users_circle-08"></fg-input>
                        <div v-if="!errors.requireName" class="check col-md-6 col-12">필수 정보입니다.</div>
                        <fg-input class="col-md-6 col-12" style="float: none; margin:0 0 0 230px; padding-bottom: 0px;" placeholder="id" v-model="user.userId" @blur="checkID()" addon-left-icon="now-ui-icons text_caps-small"></fg-input>
                        <div class="col-md-2 col-12" style="float: none; margin:0 0 0 0; padding: 7px 0 0 0;">
                            <n-button size="sm" type="primary">인증</n-button>
                        </div>
                        <div class="check col-md-6 col-12" v-if="!errors.requireID">필수 정보입니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors.maxID">
                            최대 16자까지 입력 가능합니다.</div>
                        <fg-input class="col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="checkPass()" placeholder="password" v-model="user.userPass" addon-left-icon="now-ui-icons text_caps-small"></fg-input>
                        <div class="check col-md-6 col-12" v-if="!errors.requirePass" style="color:red;">필수 정보입니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors.minPass" style="color:red;">
                            최소 9 글자까지 입력 해야 합니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors.maxPass" style="color:red;">
                            최대 16 글자까지 입력 가능합니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors.matchPass" style="color:red;">
                            비밀번호는 영문, 숫자, 특수문자가 조합되어야합니다.</div>
                        <fg-input class="col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="matchPass()" placeholder="passwordcheck" v-model="passwordcheck" addon-left-icon="now-ui-icons text_caps-small"></fg-input>
                        <div class="check col-md-6 col-12" v-if="!errors.checkPass" style="">비밀번호가 일치하지 않습니다.</div>
                        <fg-input class="col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="checkEmail()" placeholder="Email" v-model="user.userEmail" addon-left-icon="now-ui-icons ui-1_email-85"></fg-input>
                        <div class="check col-md-6 col-12" v-if="!errors.requireEmail">필수 정보입니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors.matchEmail">이메일 형식이 맞지 않습니다.</div>
                        <fg-input class="col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="checkPhone()" placeholder="phone number" v-model="user.userPhone" addon-left-icon="now-ui-icons ui-1_email-85"></fg-input>
                        <div class="check col-md-6 col-12" v-if="!errors.requirePhone">필수 정보입니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors.matchPhone">전화번호 형식이 맞지 않습니다.</div>
                    </div>
                    <div class="col-md-2 col-6" style="float: none; margin:0 auto;">
                        <div class="text-center">
                            <a class="btn btn-primary btn-round btn-lg btn-block">회원가입</a>
                        </div>
                    </div>
                </tab-pane>
                <tab-pane>
                    <span slot="label" @click="click()">
                        <i class="now-ui-icons business_badge"></i>
                        기업회원
                    </span>
                    <div class="row ml-auto mr-auto">
                        <fg-input class="col-md-6 col-12" style="float: none; margin:0 0 0 230px; padding-bottom: 0px;" placeholder="business Number" v-model="user.userPass" addon-left-icon="now-ui-icons text_caps-small"></fg-input>
                        <div class="col-md-2 col-12" style="float: none; margin:0 0 0 0; padding: 7px 0 0 0;">
                            <n-button size="sm" type="primary">인증</n-button>
                        </div>
                        <fg-input class="input col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="checkName()" placeholder="Name" v-model="user.userName" addon-left-icon="now-ui-icons users_circle-08"></fg-input>
                        <div v-if="!errors.requireName" class="check col-md-6 col-12">필수 정보입니다.</div>
                        <fg-input class="col-md-6 col-12" style="float: none; margin:0 0 0 230px; padding-bottom: 0px;" placeholder="id" v-model="user.userId" @blur="checkID()" addon-left-icon="now-ui-icons text_caps-small"></fg-input>
                        <div class="col-md-2 col-12" style="float: none; margin:0 0 0 0; padding: 7px 0 0 0;">
                            <n-button size="sm" type="primary">확인</n-button>
                        </div>
                        <div class="check col-md-6 col-12" v-if="!errors.requireID">필수 정보입니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors.maxID">
                            최대 16자까지 입력 가능합니다.</div>
                        <fg-input class="col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="checkPass()" placeholder="password" v-model="user.userPass" addon-left-icon="now-ui-icons text_caps-small"></fg-input>
                        <div class="check col-md-6 col-12" v-if="!errors.requirePass" style="color:red;">필수 정보입니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors.minPass" style="color:red;">
                            최소 9 글자까지 입력 해야 합니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors.maxPass" style="color:red;">
                            최대 16 글자까지 입력 가능합니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors.matchPass" style="color:red;">
                            비밀번호는 영문, 숫자, 특수문자가 조합되어야합니다.</div>
                        <fg-input class="col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="matchPass()" placeholder="passwordcheck" v-model="passwordcheck" addon-left-icon="now-ui-icons text_caps-small"></fg-input>
                        <div class="check col-md-6 col-12" v-if="!errors.checkPass" style="">비밀번호가 일치하지 않습니다.</div>
                        <fg-input class="col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="checkEmail()" placeholder="Email" v-model="user.userEmail" addon-left-icon="now-ui-icons ui-1_email-85"></fg-input>
                        <div class="check col-md-6 col-12" v-if="!errors.requireEmail">필수 정보입니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors.matchEmail">이메일 형식이 맞지 않습니다.</div>
                        <fg-input class="col-md-7 col-12" style="float: none;margin: 0px auto; padding-bottom: 0px;" @blur="checkPhone()" placeholder="phone number" v-model="user.userPhone" addon-left-icon="now-ui-icons ui-1_email-85"></fg-input>
                        <div class="check col-md-6 col-12" v-if="!errors.requirePhone">필수 정보입니다.</div>
                        <div class="check col-md-6 col-12" v-if="!errors.matchPhone">전화번호 형식이 맞지 않습니다.</div>
                    </div>
                    <div class="col-md-2 col-6" style="float: none; margin:0 auto;">
                        <div class="text-center">
                            <a class="btn btn-primary btn-round btn-lg btn-block">회원가입</a>
                        </div>
                    </div>
                </tab-pane>
            </tabs>
            <!-- </div> -->
        </div>
    </div>
</template>
<script>
    import {Tabs, TabPane, FormGroupInput, Button} from '@/components';
    import {Alert} from '@/components'
    import {mapActions, mapGetters} from 'vuex';
    export default {
        name: 'profile',
        bodyClass: 'profile-page',
        components: {
            Tabs,
            TabPane,
            [FormGroupInput.name]: FormGroupInput,
            [Button.name]: Button,
            Alert
        },
        data: function () {
            return {
                isBusiness: false,
                user: {
                    userName: "",
                    userId: "",
                    userPass: "",
                    userPhone: "",
                    userEmail: ""
                },
                errors: {
                    requireID: true,
                    maxID: true,
                    requirePass: true,
                    minPass: true,
                    maxPass: true,
                    checkPass: true,
                    matchPass: true,
                    requireName: true,
                    requirePhone: true,
                    requireCarrier: true,
                    matchPhone: true,
                    requireEmail: true,
                    matchEmail: true,
                    checkEmail: false,
                    requireCode: false
                },
                passwordcheck: ""
            };
        },
        computed: {
            ...mapGetters('user', ['getUserInfo'])
        },
        update(){
          this.click();
        },
        methods: {
            ...mapActions("user", ["requestRegister"]),
            clickRegister() { // console.log(this.user);
                this.requestRegister(this.user);
            },
            click() {
              console.log(this.isBusiness);
              this.isBusiness = true;
                this.user.userName = "";
            },
            clickMember() {
              console.log(this.isBusiness);
                this.isBusiness = false;
                this.user.userName = "";
            },
            checkName() {
                if (!this.user.userName) {
                    this.errors.requireName = false;
                    return;
                } else {
                    this.errors.requireName = true;
                }
            },
            checkID() {
                if (!this.user.userId) {
                    this.errors.requireID = false;
                    this.errors.maxID = true;
                } else if (this
                        .user
                        .userId
                        .length > 16) {
                    this.errors.requireID = true;
                    this.errors.maxID = false;
                } else {
                    this.errors.requireID = true;
                    this.errors.maxID = true;
                }
                return;
            },
            checkPass() {
                if (this.user.userPass == '') {
                    this.errors.requirePass = false;
                    this.errors.minPass = true;
                    this.errors.maxPass = true;
                    this.errors.matchPass = true;
                    return;
                } else if (this
                        .user
                        .userPass
                        .length < 9) {
                    this.errors.requirePass = true;
                    this.errors.minPass = false;
                    this.errors.maxPass = true;
                    this.errors.matchPass = true;
                } else if (this
                        .user
                        .userPass
                        .length > 15) {
                    this.errors.requirePass = true;
                    this.errors.minPass = true;
                    this.errors.maxPass = false;
                    this.errors.matchPass = true;
                } else if (!/^(?=.*[A-Za-z])(?=.*\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\d~!@#$%^&*()+|=]{8,16}$/.test(this.user.userPass)) {
                    this.errors.requirePass = true;
                    this.errors.minPass = true;
                    this.errors.maxPass = true;
                    this.errors.matchPass = false;
                } else {
                    this.errors.requirePass = true;
                    this.errors.minPass = true;
                    this.errors.maxPass = true;
                    this.errors.matchPass = true;
                }
                return;
            },
            matchPass() {
                if (this.user.userPass != this.passwordcheck) {
                    this.errors.checkPass = false;
                    return;
                } else {
                    this.errors.checkPass = true;
                }
            },
            checkEmail() {
                if (!this.user.email) {
                    this.errors.requireEmail = false;
                    this.errors.matchEmail = true;
                    return;
                } else if (!/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/.test(this.user.email)) {
                    this.errors.requireEmail = true;
                    this.errors.matchEmail = false;
                    return;
                } else {
                    this.errors.requireEmail = true;
                    this.errors.matchEmail = true;
                    return;
                }
            },
            checkPhone() {
                if (!this.user.phone) {
                    this.errors.requirePhone = false;
                    this.errors.matchPhone = true;
                    return;
                } else if (!/01[016789]-[^0][0-9]{2,3}-[0-9]{3,4}/.test(this.user.phone)) {
                    this.errors.requirePhone = true;
                    this.errors.matchPhone = false;
                    return;
                } else {
                    this.errors.requirePhone = true;
                    this.errors.matchPhone = true;
                    return;
                }
            }
        }
    };
</script>
<style
    lang="scss"
    scoped="scoped">
    .input .form-control,
    .form-group .el-input__inner,
    .el-date-picker .el-input .el-input__inner {
        background-color: #ffffff !important
    }
    .check {
        text-align: center;
        float: none;
        margin: 0 auto;
        padding-top: 0;
        color: red;
    }
</style>>