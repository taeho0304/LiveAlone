const URL = "3.38.97.229";

module.exports = {
  css: {
    // Enable CSS source maps.
    sourceMap: process.env.NODE_ENV !== 'production'
  },
  devServer:{
    overlay:false
  },
  transpileDependencies: [
    'vuetify'
  ]
};
