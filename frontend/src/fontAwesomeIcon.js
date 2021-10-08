import Vue from "vue";
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

import { faLink, faRedo, faUndo } from "@fortawesome/free-solid-svg-icons";
import { faHeart as fasHeart } from '@fortawesome/free-solid-svg-icons'
import { faHeart as farHeart } from '@fortawesome/free-regular-svg-icons'
import { faTrashAlt } from "@fortawesome/free-regular-svg-icons";

library.add(faTrashAlt);
library.add(faLink, faRedo, faUndo);
library.add(
    fasHeart,
    farHeart
)
Vue.component("font-awesome-icon", FontAwesomeIcon);

