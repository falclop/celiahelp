FROM httpd:2.4

# Limpia el contenido HTML por defecto
RUN rm -rf /usr/local/apache2/htdocs/*

# Copia el contenido del build
COPY ./dist/ /usr/local/apache2/htdocs/

EXPOSE 80
